package com.example.demo.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data //@Getter @Setter @to String  equals
@Table(name = "materials")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    private String description;//وصف للماده اوالpdf الى نزل

    @Column(nullable = false)
    private String fileUrl;// pdfurl , videoUrl

    private String type;// PDF VIDEO DOC IMACE
    private String category;//تصنيف الماده
    private Integer duration;//تعرف مده الفديو
    private Integer pageCount;// عدد صفحات الpdf

    @Column(nullable = false)
    private String uploadedBy;// مين رفع الملف
    @PrePersist//وقت وتاريخ رفع الملف ويتعمل تلقائى اول لما ارفع الملف
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (uploadedBy == null) {
            uploadedBy = "system"; // أو أخذها من المستخدم الحالي
        }
    }
    private Long downloadCount = 0L;  // عدد تحميلات الملف
    private Boolean isActive = true;  // الماده متاحه ؟

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

}