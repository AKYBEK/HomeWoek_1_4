public static int bossHealth = 700;
public static int bossDamage = 50;
public static String bossDefence;
public static int[] heroesHealth = {290, 270, 250, 260};
public static int[] heroesDamage = {20, 15, 10, 5};
public static String[] heroesAttackType = {"Physical", "Magical", "Piercing", "Healer"};
public static int roundNumber = 0;

public static void main(String[] args) {
    showStatistics();
    while (!isGameOver()) {
        playRound();
    }
}

public static boolean isGameOver() {
    if (bossHealth <= 0) {
        System.out.println("Heroes won!!!");
        return true;
    }
    boolean allHeroesDead = true;
    for (int i = 0; i < heroesHealth.length; i++) {
        if (heroesHealth[i] > 0) {
            allHeroesDead = false;
            break;
        }
    }
    if (allHeroesDead) {
        System.out.println("Boss won!!!");
    }
    return allHeroesDead;
}

public static void chooseBossDefence() {
    Random random = new Random();
    int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2,3
    bossDefence = heroesAttackType[randomIndex];
}

public static void playRound() {
    roundNumber++;
    chooseBossDefence();
    bossAttacks();
    heroesAttack();
    showStatistics();
}

public static void bossAttacks() {
    for (int i = 0; i < heroesHealth.length; i++) {
        if (heroesHealth[i] > 0) {
            heroesHealth[i] = heroesHealth[i] - bossDamage;
            if (heroesHealth[i] < 0) {
                heroesHealth[i] = 0;
            }
        }
    }
}

public static void heroesAttack() {
    for (int i = 0; i < heroesDamage.length; i++) {
        if (heroesHealth[i] > 0 && bossHealth > 0) {
            int damage = heroesDamage[i];
            if (heroesAttackType[i] == bossDefence) {
                Random random = new Random();
                int coeff = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                damage = heroesDamage[i] * coeff;
                System.out.println("Critical damage: " + damage);
            }
            bossHealth = bossHealth - damage;
            if (bossHealth < 0) {
                bossHealth = 0;
            }
        }
    }
    if (heroesHealth[3] < 100) {
        heroesHealth[3] += 10; // Heal the weakest hero by 10
    }
}

public static void showStatistics() {
    System.out.println("ROUND " + roundNumber + " -------------");
    System.out.println("Boss health: " + bossHealth + " damage: "
            + bossDamage + " defence: " + (bossDefence == null ? "No defence" : bossDefence));
    for (int i = 0; i < heroesHealth.length; i++) {
        System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + " damage: "
                + heroesDamage[i]);
    }
}
