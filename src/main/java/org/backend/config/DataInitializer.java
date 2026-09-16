package org.backend.config;

import lombok.RequiredArgsConstructor;
import org.backend.model.Category;
import org.backend.model.Product;
import org.backend.model.Project;
import org.backend.model.User;
import org.backend.repository.CategoryRepository;
import org.backend.repository.ProductRepository;
import org.backend.repository.ProjectRepository;
import org.backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initDatabase(ProductRepository productRepository,
                                   CategoryRepository categoryRepository,
                                   UserRepository userRepository,
                                   ProjectRepository projectRepository) {
        return args -> {
            // Kiểm tra nếu chưa có dữ liệu thì mới thêm (để tránh bị lặp mỗi lần restart)
            if (userRepository.count() == 0) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("123456"));
                userRepository.save(admin);
                System.out.println(">> Đã tạo tài khoản Admin mặc định: admin / 123456");
            }

            if (categoryRepository.count() == 0) {
                // 1. Tạo và lưu Category
                Category gach = new Category();
                gach.setName("Gạch Xây Dựng");
                categoryRepository.save(gach);

                Category xiMang = new Category();
                xiMang.setName("Xi Măng");
                categoryRepository.save(xiMang);

                // 2. Tạo và lưu Product gắn với Category tương ứng
                productRepository.save(Product.builder()
                        .name("Gạch ống 4 lỗ")
                        .price(new BigDecimal("1200"))
                        .unit("Viên")
                        .imageUrl("https://i.pinimg.com/736x/52/e9/09/52e9096c6b3c9514c56d44c9a9548b20.jpg")
                        .category(gach) // Gán đối tượng gach đã lưu
                        .build());

                productRepository.save(Product.builder()
                        .name("Xi măng Hà Tiên")
                        .price(new BigDecimal("89000"))
                        .unit("Bao")
                        .imageUrl("https://i.pinimg.com/1200x/f9/3d/b3/f93db337f77d548e1fb861630f04093e.jpg")
                        .category(xiMang) // Gán đối tượng xiMang đã lưu
                        .build());

                System.out.println(">> Đã thêm dữ liệu mẫu vào MySQL!");
            }

            if (projectRepository.count() == 0) {
                projectRepository.save(Project.builder()
                        .name("Toà nhà LP Bank")
                        .location("Trần Quang Khải - Hoàn Kiếm - Hà Nội")
                        .category("Cao ốc văn phòng")
                        .year("2023")
                        .materials("Xi măng Xuân Thành, Thép Hòa Phát, Cát đá xây dựng")
                        .imageUrl("https://images.unsplash.com/photo-1542362567-b07eac79094f?auto=format&fit=crop&w=800&q=80")
                        .description("Cung cấp toàn bộ xi măng mác cao và thép xây dựng cho kết cấu móng và dầm sàn tòa nhà trụ sở chính LP Bank.")
                        .build());

                projectRepository.save(Project.builder()
                        .name("Vinhomes Green Paradise")
                        .location("xã Cần Giờ - Tp. HCM")
                        .category("Đô thị sinh thái")
                        .year("2024")
                        .materials("Xi măng bền sunfat Xuân Thành, Cát san lấp, Đá 1x2")
                        .imageUrl("https://images.unsplash.com/photo-1506973035872-a4ec16b8e8d9?auto=format&fit=crop&w=800&q=80")
                        .description("Huy Trần vinh dự là nhà cung ứng chính các loại xi măng chịu mặn, bền sunfat phục vụ cho các hạng mục nền móng và công trình ven biển của đại đô thị.")
                        .build());

                projectRepository.save(Project.builder()
                        .name("Hầm chui Thanh Xuân")
                        .location("Thanh Xuân - Hà Nội")
                        .category("Hạ tầng giao thông")
                        .year("2022")
                        .materials("Bê tông thương phẩm, Thép dự ứng lực, Xi măng Xuân Thành")
                        .imageUrl("https://images.unsplash.com/photo-1549813069-f913d8951004?auto=format&fit=crop&w=800&q=80")
                        .description("Hỗ trợ cung cấp vật tư xi măng cường độ cao cho đơn vị thi công đúc dầm và tường vây hầm chui nút giao Thanh Xuân.")
                        .build());

                projectRepository.save(Project.builder()
                        .name("Cảng hàng không Quốc tế Phú Quốc")
                        .location("Phú Quốc - Kiên Giang")
                        .category("Hạ tầng hàng không")
                        .year("2021")
                        .materials("Xi măng Xuân Thành, Thép Hòa Phát, Cát xây tô")
                        .imageUrl("https://images.unsplash.com/photo-1436491865332-7a61a109cc05?auto=format&fit=crop&w=800&q=80")
                        .description("Cung ứng hàng chục ngàn tấn xi măng Xuân Thành và thép cuộn Hòa Phát xây dựng đường băng và nhà ga hành khách T2.")
                        .build());

                projectRepository.save(Project.builder()
                        .name("Tổ hợp du lịch Sonasea Villa & Resort")
                        .location("Phú Quốc - Kiên Giang")
                        .category("Khu nghỉ dưỡng 5 sao")
                        .year("2023")
                        .materials("Xi măng Xuân Thành, Gạch xây xây dựng, Đá trang trí")
                        .imageUrl("https://images.unsplash.com/photo-1540555700478-4be289fbecef?auto=format&fit=crop&w=800&q=80")
                        .description("Cung cấp gạch xây tiêu chuẩn và xi măng xây tô hoàn thiện cho khu biệt thự nghỉ dưỡng ven biển Sonasea.")
                        .build());

                projectRepository.save(Project.builder()
                        .name("Nhà máy Xi măng Xuân Thành")
                        .location("Thanh Liêm - Hà Nam")
                        .category("Công nghiệp nặng")
                        .year("2022")
                        .materials("Thép kết cấu, Bê tông cường độ siêu cao, Cát đá xây dựng")
                        .imageUrl("https://images.unsplash.com/photo-1581092160607-ee22621dd758?auto=format&fit=crop&w=800&q=80")
                        .description("Cung cấp vật tư kết cấu thép và vật liệu xây dựng cho giai đoạn mở rộng dây chuyền sản xuất số 3 của nhà máy.")
                        .build());

                System.out.println(">> Đã thêm dữ liệu công trình tiêu biểu mẫu vào MySQL!");
            }
        };
    }
}