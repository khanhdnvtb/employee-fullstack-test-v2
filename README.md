# Employee Application (Kotlin)

Đây là phiên bản Kotlin của ứng dụng Employee Management, được chuyển đổi từ project Java gốc.

## Tính năng

- Quản lý sản phẩm với các thông tin: title, handle, vendor, product type
- Hỗ trợ variants và images cho sản phẩm
- Import dữ liệu tự động từ API bên ngoài
- Giao diện web sử dụng Thymeleaf và HTMX
- Database PostgreSQL với Flyway migration

## Cấu trúc Project

```
src/main/kotlin/com/employeefullstack/employee/
├── constant/
│   └── ProductType.kt
├── controller/
│   └── ProductController.kt
├── dto/
│   ├── FeaturedImageDTO.kt
│   ├── ProductDTO.kt
│   ├── ShopProductDTO.kt
│   └── VariantDTO.kt
├── entity/
│   ├── Image.kt
│   ├── Product.kt
│   └── Variant.kt
├── repository/
│   ├── ImageRepository.kt
│   ├── ProductRepository.kt
│   └── VariantRepository.kt
├── service/
│   ├── ImportDataService.kt
│   └── ProductService.kt
└── EmployeeApplication.kt
```

## Cài đặt và Chạy

### Yêu cầu
- Java 21
- PostgreSQL
- Gradle

### Cấu hình Database
1. Tạo database PostgreSQL tên `product`
2. Cập nhật thông tin kết nối trong `src/main/resources/application.properties`

### Chạy ứng dụng
```bash
./gradlew bootRun
```

Ứng dụng sẽ chạy tại `http://localhost:13334`

### Build
```bash
./gradlew build
```

### Test
```bash
./gradlew test
```

## So sánh với phiên bản Java

### Những thay đổi chính:
1. **Ngôn ngữ**: Chuyển từ Java sang Kotlin
2. **Data Classes**: Sử dụng data classes thay vì Lombok annotations
3. **Null Safety**: Tận dụng null safety của Kotlin
4. **Extension Functions**: Có thể sử dụng extension functions cho các tiện ích
5. **Coroutines**: Có thể sử dụng coroutines cho async operations (nếu cần)

### Tương thích:
- Tất cả tính năng từ phiên bản Java đều được giữ nguyên
- API endpoints giống hệt
- Database schema không thay đổi
- Giao diện web giống hệt

## Cấu hình

### Application Properties
- `server.port`: Port của ứng dụng (mặc định: 13334)
- `spring.datasource.*`: Cấu hình database
- `import.initial-delay`: Delay ban đầu cho import data (ms)
- `import.fixed-delay`: Delay giữa các lần import (ms)

### Database Migration
Sử dụng Flyway để quản lý database schema. Migration files được đặt trong `src/main/resources/db/migration/`.

## API Endpoints

- `GET /`: Trang chủ
- `GET /products`: Load danh sách sản phẩm
- `GET /products/new-form`: Form tạo sản phẩm mới
- `POST /products`: Tạo sản phẩm mới

## Import Data

Ứng dụng tự động import dữ liệu từ `https://famme.no/products.json` theo lịch trình được cấu hình. 