package Main;

import java.util.Random;
import java.util.Scanner;

public class GameCombat {
    private static Random random = new Random();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int pCmd, eCmd, loop;
        int dmg, pAtk, eAtk, crit, hit, eHp, pHp, pSp, eSp;
        boolean chill;
        
        do {
            chill = false;
            loop = 0;
            pHp = 30;
            eHp = 30;
            pAtk = 7;
            eAtk = 7;
            pSp = 3;
            eSp = 3;
            do {
                do {
                    hit = random.nextInt(10);
                    dmg = random.nextInt(5) + pAtk;
                    crit = random.nextInt(10);
                    System.out.println("1 to attack");
                    System.out.println("2 to use a spell (" + pSp + ") remaining");
                    pCmd = input.nextInt();
                    if (pCmd == 2 && pSp > 0) {
                        System.out.println("Which spell do you want to use?");
                        System.out.println("1 for fire: increased damage");
                        System.out.println("2 for lightning: increased crit rate");
                        System.out.println("3 for water: guaranteed hit");
                        System.out.println("4 for ice: Enemy cannot make a defensive action");
                        System.out.println("5 to go back");
                        pCmd = input.nextInt();
                        if (pCmd >= 1 && pCmd <= 4) {
                            pSp = pSp - 1;
                            if (pCmd == 1) {
                                dmg = dmg + 8;
                            } else {
                                if (pCmd == 2) {
                                    crit = crit + 5;
                                } else {
                                    if (pCmd == 3) {
                                        hit = hit + 6;
                                    } else {
                                        if (pCmd == 4) {
                                            chill = true;
                                        }
                                    }
                                }
                            }
                            hit = hit - 2;
                        }
                    } else {
                        if (pCmd == 2 && pSp == 0) {
                            System.out.println("You're out of spells");
                            pCmd = 3;
                        }
                    }
                } while (pCmd < 1 || pCmd > 4);
                if (chill == true) {
                    chill = false;
                    System.out.println("The enemy can't react!");
                } else {
                    eCmd = random.nextInt(2);
                    if (eCmd == 0) {
                        System.out.println("The enemy defends!");
                        dmg = dmg / 2;
                    } else {
                        System.out.println("The enemy attempts to dodge!");
                        hit = hit - 3;
                    }
                }
                if (hit > 0) {
                    if (crit > 8) {
                        dmg = dmg * 3;
                        System.out.println("Critical hit!");
                    }
                    eHp = eHp - dmg;
                    System.out.println("You dealt " + dmg + " damage");
                } else {
                    System.out.println("You missed!");
                }
                if (eHp > 0) {
                    System.out.println("Enemy has " + eHp + " health left");
                    hit = random.nextInt(10);
                    dmg = random.nextInt(5) + eAtk;
                    crit = random.nextInt(10);
                    if (eSp > 0) {
                        eCmd = random.nextInt(2);
                    } else {
                        eCmd = 0;
                    }
                    if (eCmd == 1) {
                        eSp = eSp - 1;
                        eCmd = random.nextInt(4);
                        if (eCmd == 0) {
                            dmg = dmg + 8;
                        } else {
                            if (eCmd == 1) {
                                crit = crit + 5;
                            } else {
                                if (eCmd == 2) {
                                    hit = hit + 6;
                                } else {
                                    if (eCmd == 3) {
                                        chill = true;
                                    }
                                }
                            }
                        }
                        hit = hit - 2;
                    }
                    if (chill == true) {
                        chill = false;
                    } else {
                        do {
                            System.out.println("1 to defend against the enemy's attack");
                            System.out.println("2 to attempt a dodge");
                            pCmd = input.nextInt();
                            if (pCmd != 1 && pCmd != 2) {
                                System.out.println("No");
                            }
                        } while (pCmd != 1 && pCmd != 2);
                        if (pCmd == 1) {
                            dmg = dmg / 2;
                        } else {
                            if (pCmd == 2) {
                                hit = hit - 3;
                            }
                        }
                    }
                    if (eCmd == 0) {
                        System.out.println("The enemy uses fire!");
                    } else {
                        if (eCmd == 1) {
                            System.out.println("The enemy uses lightning!");
                        } else {
                            if (eCmd == 2) {
                                System.out.println("The enemy uses water!");
                            } else {
                                if (eCmd == 3) {
                                    System.out.println("The enemy uses ice!");
                                    System.out.println("You can't react!");
                                }
                            }
                        }
                    }
                    if (hit > 0) {
                        if (crit > 8) {
                            dmg = dmg * 3;
                            System.out.println("Critical hit!");
                        }
                        pHp = pHp - dmg;
                        System.out.println("You took " + dmg + " damage");
                    } else {
                        System.out.println("The enemy missed!");
                    }
                    if (pHp > 0) {
                        System.out.println("You have " + pHp + " health left");
                    } else {
                        System.out.println("You just got clapped lmao");
                        loop = 1;
                    }
                } else {
                    System.out.println("The enemy has been defeated");
                    System.out.println("#1 victory royale");
                    loop = 1;
                }
            } while (loop == 0);
            System.out.println("Enter 1 to battle again");
            System.out.println("Enter 2 to quit");
            loop = input.nextInt();
        } while (loop == 1);
    }
}
