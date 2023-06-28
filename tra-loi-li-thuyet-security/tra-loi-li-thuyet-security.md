**1. Trình bày khái niệm liên quan đến các đối tượng sau:**

- **UserNamePasswordAuthenticationToken:** là một class cung cấp các constructor có 2 tác dụng chính là tạo đối tượng xác thực và tạo đối tượng phân quyền với thông tin đầu vào là thông tin người dùng đăng nhập.

\-  Constructor UsernamePasswordAuthenticationToken(Object principal, Object credentials) dùng để khởi tạo đối tượng xác thực (*lúc user login - sau đó được xử lí xác thực bởi AuthenticationManager*);

\- UsernamePasswordAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) sử dụng để tạo đối tượng phân quyền cho user (*sau khi login thành công - sau đó được lưu vào Security Context Holder, và chuyển qua các filter tiếp theo*);

Trong đó:

\+  principal: Là tên user từ loginRequest (thường là username/ email)

\+ credentials: Là password của user từ loginRequest;

\+ authorities: Là danh sách roles (quyền) của user.

- **AuthenticationManager:** là trình quản lí xác thực, bản chất là một interface với 1 method duy nhất là authenticate(), với tham số đầu vào là UsernamePasswordAuthenticationToken, trả về đối tượng xác thực Authentication nếu thành công, hoặc trả về AuthenticationException nếu thất bại;
- **AuthenticationProvider**: bản chất là 1 interface, có thể được khởi tạo bằng nhiều class, nhưng thường sử dụng DaoAuthenticationProvider để triển khai, nhận 2 tham số đầu vào là PasswordEncoder (cung cấp chức năng mã hóa và so sánh mật khẩu) và UserDetailsService (cung cấp chức năng tìm kiếm user).

Đối tượng AuthenticationProvider sau đó được gọi trong filterChain để cung cấp các phương pháp 	xác 	thực username, password, tìm kiếm user khi login.

- **PasswordEncoder:** là method sử dụng 1 thuật toán để mã hóa và so sánh password, có nhiều thuật toán nhưng thông thường sử dụng BcryptPasswordEncoder();
- **UserDetailsService:** là 1 interface triển khai từ class Service của UserDetails, cung cấp phương thức tìm kiếm user theo username (thường là username/ email)
- **UserDetails:** là 1 interface triển khai từ class User, cung cấp các phương thức lấy thông tin user (danh sách quyền, username, password, các trạng thái của account)

**3. Session là gì? Cookie là gì? Nêu sự khác biệt giữa session và cookie**

**Session** (còn gọi là một phiên làm việc) là công cụ lưu trữ dữ liệu của người dùng khi sử dụng website. Khi client gửi request lên server, một session được tạo ra ở phía server và lưu lại các hành động/thông tin của user cho đến khi người dùng đóng trình duyệt hoặc hết hạn session.

**Cookie** là 1 đoạn văn bản được khởi tạo và lưu trữ ở phía client. Khi một session được tạo ra, session id được lưu trữ vào trong cookie, cookie sau đó được gửi và lưu trữ ở phía client. Khi người dùng truy cập vào trang web có sử dụng cookie, server sẽ sử dụng các thông tin được lưu trữ trong cookie để nhận dạng người dùng trong những lần truy cập sau. Cookie sẽ được lưu trữ cho đến khi hết hạn (do lập trình viên xác định trước) hoặc khi bị người dùng xóa.

**Sự khác nhau giữa session và cookie**


||Session|Cookie|
| - | - | - |
|Nơi lưu trữ|Bên phía server|Bên phía Client|
|Thời hạn lưu trữ|Đến hết phiên làm việc, khi người dùng đóng trình duyệt hoặc khi hết hạn session.|Được lưu trữ trong trình duyệt cho đến khi hết hạn hoặc khi bị xóa.|
|Khả năng xâm nhập|Khó bị can thiệp hay sửa đổi|Dễ bị xâm nhập và sửa đổi|

**

**2. Dựa vào hiểu biết của em. Hãy trình bày sơ lược về workflow xử lý trong Spring Security**

![IMG_256](Aspose.Words.4f4c495d-7b45-4bbb-95e8-9aeaf297e5c9.001.png)

1. User gửi thông tin login lên server (login request)

//Tạo đối tượng xác thực và tiến hành xác thực

1. Đối tượng xác thực được tạo ra bằng constructor UsernamePasswordAuthenticationToken(email, password) với thông tin từ request của người dùng.

1. Gọi phương thức tương ứng của AuthenticationManager với tham số đầu vào là UsernamePasswordAuthenticationToken (email, password) , để tiến hành xác thực. 

1. ` `AuthenticationProvider được tạo ra.

//Từ bước 5-9 là các bước set chức năng cho provider

1. Set chức năng so sánh và mã hóa mật khẩu cho AuthenticationProvider thông qua PasswordEncoder,

1. **7-8-9** Set chức năng tìm kiếm user thông qua email/username thông qua UserDetailsService; Class User triển khai interface UserDetails để cung cấp phương thức lấy thông tin user;

10. Phương thức AuthenticationProvider sẽ được thêm vào filterChain để cung cấp pp xác thực username, password, tìm kiếm user. 

10. Nếu xác thực thành công, đối tượng xác thực sẽ được lưu vào Security Context Holder và sau đó lưu vào Session.

//Tạo đối tượng phân quyền và xử lí phân quyền

12. Bộ filter sẽ lấy thông tin email trong session, và tìm kiếm user thông qua class triển khai của UserDetailsService, tạo đối tượng phân quyền bằng UsernamePasswordAuthenticationToken(email, password, authorities) dựa trên thông tin trả về từ UserDetails.

Thông tin token sau đó được lưu vào security context holder và chuyển qua các filter tiếp theo. 
