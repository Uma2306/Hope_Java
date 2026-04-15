import java.util.*;

class Advertiser {
    private int id;
    private String name;

    public Advertiser(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void display() {
        System.out.println("Advertiser ID: " + id + ", Name: " + name);
    }
}

class Admin extends Advertiser {
    public Admin(int id, String name) {
        super(id, name);
    }

    public void activateAd(Advertisement ad) {
        ad.setActive(true);
        System.out.println("Ad activated successfully.");
    }
}

abstract class Advertisement {
    protected int adId;
    protected String title;
    protected String body;
    protected Advertiser advertiser;
    protected boolean isActive;

    public Advertisement(int adId, String title, String body, Advertiser advertiser) {
        this.adId = adId;
        this.title = title;
        this.body = body;
        this.advertiser = advertiser;
        this.isActive = false;
    }

    public int getAdId() {
        return adId;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean status) {
        isActive = status;
    }

    public abstract void displayAd();
}

class BannerAd extends Advertisement {

    public BannerAd(int adId, String title, String body, Advertiser advertiser) {
        super(adId, title, body, advertiser);
    }

    @Override
    public void displayAd() {
        System.out.println("\n----- Advertisement -----");
        System.out.println("Ad ID      : " + adId);
        System.out.println("Title      : " + title);
        System.out.println("Body       : " + body);
        System.out.println("Advertiser : " + advertiser.getName());
        System.out.println("Status     : " + (isActive ? "Active" : "Inactive"));
        System.out.println("--------------------------");
    }
}

class AdManager {

    private Map<Integer, Advertiser> advertisers = new HashMap<>();
    private Map<Integer, Advertisement> ads = new HashMap<>();

    public void addAdvertiser(Advertiser a) {
        advertisers.put(a.getId(), a);
        System.out.println("Advertiser added.");
    }

    public void viewAdvertisers() {
        if (advertisers.isEmpty()) {
            System.out.println("No advertisers.");
            return;
        }

        for (Advertiser a : advertisers.values()) {
            a.display();
        }
    }

    public Advertiser findAdvertiser(int id) {
        return advertisers.get(id);
    }

    public void addAd(Advertisement ad) {
        ads.put(ad.getAdId(), ad);
        System.out.println("Ad added.");
    }

    public void viewAds(String type) {
        if (ads.isEmpty()) {
            System.out.println("No ads available.");
            return;
        }

        boolean found = false;

        for (Advertisement ad : ads.values()) {
            if (type.equalsIgnoreCase("all")) {
                ad.displayAd();
                found = true;
            }
            else if (type.equalsIgnoreCase("active") && ad.isActive()) {
                ad.displayAd();
                found = true;
            }
            else if (type.equalsIgnoreCase("inactive") && !ad.isActive()) {
                ad.displayAd();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching ads.");
        }
    }

    public Advertisement findAd(int id) {
        return ads.get(id);
    }

    public void deleteAd(int id) {
        if (ads.remove(id) != null) {
            System.out.println("Ad deleted.");
        } else {
            System.out.println("Ad not found.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        AdManager manager = new AdManager();
        Admin admin = new Admin(1, "Admin");

        int choice;

        do {
            System.out.println("\n===== ADVERTISEMENT SYSTEM =====");
            System.out.println("1. Add Advertiser");
            System.out.println("2. View Advertisers");
            System.out.println("3. Add Advertisement");
            System.out.println("4. View Ads");
            System.out.println("5. Activate Ad");
            System.out.println("6. Delete Ad");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter Advertiser ID: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    manager.addAdvertiser(new Advertiser(id, name));
                    break;

                case 2:
                    manager.viewAdvertisers();
                    break;

                case 3:
                    System.out.print("Enter Advertiser ID: ");
                    int aid = sc.nextInt();
                    sc.nextLine();

                    Advertiser adv = manager.findAdvertiser(aid);
                    if (adv == null) {
                        System.out.println("Advertiser not found.");
                        break;
                    }

                    System.out.print("Enter Ad ID: ");
                    int adId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Body: ");
                    String body = sc.nextLine();

                    manager.addAd(new BannerAd(adId, title, body, adv));
                    break;

                case 4:
                    System.out.print("Enter type (all/active/inactive): ");
                    String type = sc.nextLine();
                    manager.viewAds(type);
                    break;

                case 5:
                    System.out.print("Enter Ad ID to activate: ");
                    int actId = sc.nextInt();

                    Advertisement ad = manager.findAd(actId);
                    if (ad != null) {
                        admin.activateAd(ad);
                    } else {
                        System.out.println("Ad not found.");
                    }
                    break;

                case 6:
                    System.out.print("Enter Ad ID to delete: ");
                    int delId = sc.nextInt();
                    manager.deleteAd(delId);
                    break;

                case 7:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (choice != 7);

        sc.close();
    }
}
