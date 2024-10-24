
public class News extends BaseEntity {
    private String title;
    private String news;
    @Column(nam = "news_number")
    private Timestamp newsNumber;
    private Boolean isDeleted;

}
