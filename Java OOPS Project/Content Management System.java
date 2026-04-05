import java.util.*;

// User class
class User {
    private int userId;
    private String name;

    public User(int userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void displayUser() {
        System.out.println("User ID: " + userId + ", Name: " + name);
    }
}

// Admin class (Inheritance)
class Admin extends User {
    public Admin(int userId, String name) {
        super(userId, name);
    }

    public void publishContent(Content content) {
        content.setPublished(true);
        System.out.println("Content published successfully.");
    }
}

// Abstract class (Abstraction)
abstract class Content {
    protected int contentId;
    protected String title;
    protected String body;
    protected User author;
    protected boolean isPublished;

    public Content(int contentId, String title, String body, User author) {
        this.contentId = contentId;
        this.title = title;
        this.body = body;
        this.author = author;
        this.isPublished = false;
    }

    public int getContentId() {
        return contentId;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public User getAuthor() {
        return author;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setPublished(boolean isPublished) {
        this.isPublished = isPublished;
    }

    public abstract void displayContent();
}

// Article class (Inheritance + Polymorphism)
class Article extends Content {
    public Article(int contentId, String title, String body, User author) {
        super(contentId, title, body, author);
    }

    @Override
    public void displayContent() {
        System.out.println("\n----- Article Details -----");
        System.out.println("Content ID : " + contentId);
        System.out.println("Title      : " + title);
        System.out.println("Body       : " + body);
        System.out.println("Author ID  : " + author.getUserId());
        System.out.println("Author     : " + author.getName());
        System.out.println("Published  : " + (isPublished ? "Yes" : "No"));
        System.out.println("---------------------------");
    }
}

// CMS Manager class
class CMSManager {
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Content> contentList = new ArrayList<>();

    // Add user
    public void addUser(User user) {
        users.add(user);
        System.out.println("User added successfully.");
    }

    // View users
    public void viewUsers() {
        if (users.isEmpty()) {
            System.out.println("No users available.");
            return;
        }

        System.out.println("\n----- User List -----");
        for (User u : users) {
            u.displayUser();
        }
    }

    // Find user by ID
    public User findUserById(int id) {
        for (User u : users) {
            if (u.getUserId() == id) {
                return u;
            }
        }
        return null;
    }

    // Add content
    public void addContent(Content content) {
        contentList.add(content);
        System.out.println("Content added successfully.");
    }

    // View content (all / published / unpublished)
    public void viewContent(String type) {
        if (contentList.isEmpty()) {
            System.out.println("No content available.");
            return;
        }

        boolean found = false;

        for (Content c : contentList) {
            if (type.equalsIgnoreCase("all")) {
                c.displayContent();
                found = true;
            } 
            else if (type.equalsIgnoreCase("published") && c.isPublished()) {
                c.displayContent();
                found = true;
            } 
            else if (type.equalsIgnoreCase("unpublished") && !c.isPublished()) {
                c.displayContent();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching content found.");
        }
    }

    // Find content by ID
    public Content findContentById(int id) {
        for (Content c : contentList) {
            if (c.getContentId() == id) {
                return c;
            }
        }
        return null;
    }

    // Edit content
    public void editContent(int id, String newTitle, String newBody) {
        Content c = findContentById(id);
        if (c != null) {
            c.setTitle(newTitle);
            c.setBody(newBody);
            System.out.println("Content updated successfully.");
        } else {
            System.out.println("Content not found.");
        }
    }

    // Delete content
    public void deleteContent(int id) {
        Content c = findContentById(id);
        if (c != null) {
            contentList.remove(c);
            System.out.println("Content deleted successfully.");
        } else {
            System.out.println("Content not found.");
        }
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        CMSManager cms = new CMSManager();
        Admin admin = new Admin(1, "Admin");

        int choice;

        do {
            System.out.println("\n===== CONTENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add User");
            System.out.println("2. View Users");
            System.out.println("3. Add Article");
            System.out.println("4. View Content");
            System.out.println("5. Edit Content");
            System.out.println("6. Delete Content");
            System.out.println("7. Publish Content");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter User ID: ");
                    int userId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter User Name: ");
                    String userName = sc.nextLine();

                    User user = new User(userId, userName);
                    cms.addUser(user);
                    break;

                case 2:
                    cms.viewUsers();
                    break;

                case 3:
                    System.out.print("Enter Author User ID: ");
                    int authorId = sc.nextInt();
                    sc.nextLine();

                    User author = cms.findUserById(authorId);

                    if (author == null) {
                        System.out.println("User not found. Please add user first.");
                        break;
                    }

                    System.out.print("Enter Content ID: ");
                    int contentId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Body: ");
                    String body = sc.nextLine();

                    Article article = new Article(contentId, title, body, author);
                    cms.addContent(article);
                    break;

                case 4:
                    System.out.print("Enter type (all / published / unpublished): ");
                    String type = sc.nextLine();
                    cms.viewContent(type);
                    break;

                case 5:
                    System.out.print("Enter Content ID to edit: ");
                    int editId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter New Title: ");
                    String newTitle = sc.nextLine();

                    System.out.print("Enter New Body: ");
                    String newBody = sc.nextLine();

                    cms.editContent(editId, newTitle, newBody);
                    break;

                case 6:
                    System.out.print("Enter Content ID to delete: ");
                    int deleteId = sc.nextInt();
                    cms.deleteContent(deleteId);
                    break;

                case 7:
                    System.out.print("Enter Content ID to publish: ");
                    int publishId = sc.nextInt();

                    Content c = cms.findContentById(publishId);
                    if (c != null) {
                        admin.publishContent(c);
                    } else {
                        System.out.println("Content not found.");
                    }
                    break;

                case 8:
                    System.out.println("Exiting CMS Project...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 8);

    
    }
}
