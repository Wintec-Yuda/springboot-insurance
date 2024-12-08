// package com.livecode.insurance;

// import com.livecode.insurance.treatment.Treatment;
// import com.livecode.insurance.treatment.TreatmentRepository;
// import com.livecode.insurance.users.Role;
// import com.livecode.insurance.users.User;
// import com.livecode.insurance.users.UserRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Component;

// import java.util.Arrays;
// import java.util.List;

// @Component
// public class DataInitializer implements CommandLineRunner {

//     @Autowired
//     private UserRepository userRepository;

//     @Autowired
//     private PasswordEncoder passwordEncoder;

//     @Autowired
//     private TreatmentRepository treatmentRepository;

//     @Override
//     public void run(String... args) throws Exception {
//         if (userRepository.findByEmailAndIsDeletedFalse("admin@example.com") == null) {
//             User admin = new User();
//             admin.setName("Admin");
//             admin.setAddress("Admin Address");
//             admin.setPhone("081234567890");
//             admin.setEmail("admin@example.com");
//             admin.setPassword(passwordEncoder.encode("admin123"));
//             admin.setRole(Role.ADMIN);
//             admin.setIsDeleted(false);
//             userRepository.save(admin);
//         }

//         if (userRepository.findByEmailAndIsDeletedFalse("user@example.com") == null) {
//             User user = new User();
//             user.setName("User");
//             user.setAddress("User Address");
//             user.setPhone("081234567891");
//             user.setEmail("user@example.com");
//             user.setPassword(passwordEncoder.encode("user123"));
//             user.setRole(Role.USER);
//             user.setIsDeleted(false);
//             userRepository.save(user);
//         }

//         if (treatmentRepository.count() == 0) {
//             List<Treatment> treatments = Arrays.asList(
//                 new Treatment("Cardiology Coverage", 5000000L),
//                 new Treatment("Orthopedic Coverage", 4000000L),
//                 new Treatment("Dental Coverage", 2000000L),
//                 new Treatment("Vision Coverage", 1500000L),
//                 new Treatment("General Checkup", 1000000L),
//                 new Treatment("Surgical Coverage", 8000000L),
//                 new Treatment("Maternity Coverage", 7000000L),
//                 new Treatment("Pediatric Coverage", 4500000L),
//                 new Treatment("Cancer Treatment", 10000000L),
//                 new Treatment("Neurology Coverage", 8500000L),
//                 new Treatment("ENT Coverage", 3000000L),
//                 new Treatment("Dermatology Coverage", 2500000L),
//                 new Treatment("Psychiatric Coverage", 5000000L),
//                 new Treatment("Urology Coverage", 3500000L),
//                 new Treatment("Emergency Coverage", 9000000L),
//                 new Treatment("Home Care Coverage", 6000000L),
//                 new Treatment("Rehabilitation Coverage", 4000000L),
//                 new Treatment("Diabetes Management", 5000000L),
//                 new Treatment("Hypertension Management", 4500000L),
//                 new Treatment("COVID-19 Coverage", 7500000L),
//                 new Treatment("Mental Health Support", 5500000L),
//                 new Treatment("Cardiac Surgery", 12000000L),
//                 new Treatment("Liver Transplant", 15000000L),
//                 new Treatment("Kidney Dialysis", 7000000L),
//                 new Treatment("Physical Therapy", 3000000L),
//                 new Treatment("Nutrition Counseling", 2000000L),
//                 new Treatment("Immunotherapy", 8000000L),
//                 new Treatment("Alternative Medicine", 2500000L),
//                 new Treatment("Palliative Care", 6000000L),
//                 new Treatment("Sports Injury Recovery", 4000000L),
//                 new Treatment("Chronic Pain Management", 4500000L),
//                 new Treatment("Substance Abuse Treatment", 6500000L),
//                 new Treatment("Sleep Disorders Treatment", 3500000L),
//                 new Treatment("Infertility Treatment", 7500000L),
//                 new Treatment("Prostate Cancer Screening", 4000000L),
//                 new Treatment("Stroke Rehabilitation", 9000000L),
//                 new Treatment("Hearing Aid Support", 2000000L),
//                 new Treatment("Skin Allergy Treatment", 2500000L),
//                 new Treatment("Orthodontic Procedures", 5000000L),
//                 new Treatment("Vaccination Coverage", 1500000L)
//             );
//             treatmentRepository.saveAll(treatments);
//         }
//     }
// }
