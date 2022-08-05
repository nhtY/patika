import java.util.Random;

public abstract class BattleLoc extends Location{
	
	private Obstacle obstacle;
	private String award;
	private int maxObstacle;

	public BattleLoc(Player player, String locationName, Obstacle obstacle, String award, int maxObstacle) {
		super(player, locationName);
		this.obstacle = obstacle;
		this.award = award;
		this.maxObstacle = maxObstacle;
	}
	
	@Override
	boolean onLocation() {
		
		if(this.checkLocked()) {
			System.out.println("You already won "+ this.award + ". This battle location is locked now.");
			return true;
		}
		
		int randomObsNumber = this.randomObstacleNumber();
		System.out.println("Now, you are here: " + this.getLocationName());
		System.out.println("Be careful! " + randomObsNumber + " " + this.obstacle.getName() + " lives here.");
		System.out.println("<S>avaþ veya <K>aç");
		String selectBattleCase = scanner.nextLine();
		selectBattleCase = selectBattleCase.toLowerCase();
		
		if(selectBattleCase.equals("s")) {
			if(combat(randomObsNumber)) {
				System.out.println(this.getPlayer().getName() + ", you have beaten all the enemies!!");
				return true;
			}
		}
		
		if(this.getPlayer().getHealth()<=0) {
			System.out.println("You died :(");
			return false;
		}
		
		return true;
	}
	
	public boolean combat(int obsNumber) {
		for(int i=1; i<=obsNumber; i++) {
			this.getObstacle().setHealth(this.getObstacle().getOriginalHelath());
			playerStats();
			obstacleStats(i);
			
			int randomFirstHit = new Random().nextInt(2);
			System.out.println(randomFirstHit);
			boolean isFirst = true;
			
			while(this.getPlayer().getHealth()>0 && this.getObstacle().getHealth()>0) {
				
				if(isFirst) {
					if(randomFirstHit==0) {
						System.out.println("<H>it or <E>scape:");
						String selectCombat = scanner.nextLine().toLowerCase();
						if(selectCombat.equals("h")) {
							System.out.println("\nYou hit the monster");
							this.obstacle.setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
							afterHit(i);
							monsterHits(i);
						}else {
							return false;
						}
					}else {
						monsterHits(i);
					}
				}else {
					System.out.println("<H>it or <E>scape:");
					String selectCombat = scanner.nextLine().toLowerCase();
					if(selectCombat.equals("h")) {
						System.out.println("\nYou hit the monster");
						this.obstacle.setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
						afterHit(i);
						
						monsterHits(i);
						
					}else {
						return false;
					}
				}
				isFirst = false;
				
			}
			if(this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
				if(!(this.getObstacle() instanceof Snake)) {
					System.out.println("\nYou killed the enemy and won money");
					int updatedMoney = this.getPlayer().getMoney() + this.getObstacle().getAward();
					this.getPlayer().setMoney(updatedMoney);
					System.out.println("Money updated: " + updatedMoney);
				}else {
					this.getSnakeAward();
				}
			}else {
				return false;
			}
			
		}
		System.out.println("All the enemies were killed. You won: "+ this.award);
		this.wonAward();
		return true;
	}
	
	private void getSnakeAward() {
		
		String message =  pickRandomAward();
		System.out.println("You killed a SNAKE.\n"
				+ message);
		
	}
	
	private String pickRandomAward() {
		int ratio = new Random().nextInt(100)+1;
		boolean wonWeapon = 0<ratio && ratio<=15;
		boolean wonArmor = 15<ratio && ratio<=30;
		boolean wonMoney = 30<ratio && ratio<=55;
		
		if(wonWeapon) {
			return this.pickWeapon();
		}
		
		if(wonArmor) {
			return this.pickArmor();
		}
		
		if(wonMoney) {
			return this.pickMoney();
		}
		
		return "No award given :(";
	}

	private String pickWeapon() {
		int ratio = new Random().nextInt(100)+1;
		boolean wonRifle = 0<ratio && ratio<=20;
		boolean wonSword = 20<ratio && ratio<=50;
		boolean wonGun = 50<ratio && ratio<=100;
		
		Weapon[] weapons = Weapon.getWeapons();
		Weapon pickedWeapon = new Weapon("Punch", 0, 0, 0);
		if(wonRifle) {
			pickedWeapon = weapons[2];
		}
		
		if(wonSword) {
			pickedWeapon = weapons[1];
		}
		
		if(wonGun) {
			pickedWeapon = weapons[0];
		}
		
		this.getPlayer().getInventory().setWeapon(pickedWeapon);
		
		return "You won an weapon: "
						+ this.getPlayer().getInventory().getWeapon().getName();
	}

	private String pickArmor() {
		int ratio = new Random().nextInt(100)+1;
		boolean wonHeavy = 0<ratio && ratio<=20;
		boolean wonMiddle = 20<ratio && ratio<=50;
		boolean wonLight = 50<ratio && ratio<=100;
		
		Armor[] armors = Armor.getArmors();
		Armor pickedArmor = new Armor(0, "Rag", 0, 0);
		if(wonHeavy) {
			pickedArmor = armors[2];
		}
		
		if(wonMiddle) {
			pickedArmor = armors[1];
		}
		
		if(wonLight) {
			pickedArmor = armors[0];
		}
		
		this.getPlayer().getInventory().setArmor(pickedArmor);
		
		return "You won an armor: "
						+ this.getPlayer().getInventory().getArmor().getName();
		
	}

	private String pickMoney() {
		int ratio = new Random().nextInt(100)+1;
		boolean won10 = 0<ratio && ratio<=20;
		boolean won5 = 20<ratio && ratio<=50;
		boolean won1 = 50<ratio && ratio<=100;
		
		int award = 0;
		
		if(won10) {
			award = 10;
		}
		
		if(won5) {
			award = 5;
		}
		
		if(won1) {
			award = 1;
		}
		
		int updatedMoney = this.getPlayer().getMoney() + award;
		this.getPlayer().setMoney(updatedMoney);
		System.out.println("Money updated: " + updatedMoney);
		
		return "You earned " + award + " money!";
	}

	private boolean checkLocked() {
		String[] awardsCollected = this.getPlayer().getInventory().getAwards();
		if(this instanceof River && awardsCollected[0]!= null) {
			 return true;
		}else if(this instanceof Forest && awardsCollected[1]!=null) {
			 return true;
		}else if(this instanceof Cave && awardsCollected[2]!=null){
			return true;
		}
		return false;
	}
	
	private void monsterHits(int i) {
		if(this.getObstacle() instanceof Snake) {
			this.setSnakeDamageRandomly();
		}
		
		if(this.getObstacle().getHealth()>0) {
			System.out.println("\nThe monster hit you!");
			int obsDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
			if(obsDamage<0) {
				obsDamage = 0;
			}
			
			this.getPlayer().setHealth(this.getPlayer().getHealth() - obsDamage);
			afterHit(i);
		}
	}

	private void afterHit(int i) {
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("Your Health: " + this.getPlayer().getHealth());
		System.out.println(i + ". " + this.obstacle.getName() + "'s Health: " + (this.obstacle.getHealth()<0 ? 0:this.obstacle.getHealth()));
		System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++");
	}

	public void playerStats() {
		System.out.println("\n------------------------------");
		System.out.println("Character Condition");
		System.out.println("------------------------------");
		this.getPlayer().printPlayerInfo();	
	}
	
	public void obstacleStats(int i) {
		System.out.println("\n------------------------------");
		System.out.println(i + ". " + this.getObstacle().getName() + " Info");
		System.out.println("------------------------------");
		System.out.println("Health: " + this.getObstacle().getHealth());
		System.out.println("Damage: " + this.getObstacle().getDamage());
		System.out.println("Award: " + this.getObstacle().getAward());
	}

	public Obstacle getObstacle() {
		return obstacle;
	}

	public void setObstacle(Obstacle obstacle) {
		this.obstacle = obstacle;
	}

	public String getAward() {
		return award;
	}

	public void setAward(String award) {
		this.award = award;
	}

	public int getMaxObstacle() {
		return maxObstacle;
	}

	public void setMaxObstacle(int maxObstacle) {
		this.maxObstacle = maxObstacle;
	}
	
	public int randomObstacleNumber() {
		Random random = new Random();
		return random.nextInt(this.maxObstacle) +1; // 1-3
	}
	
	private void wonAward() {
		String[] awardsCollected = this.getPlayer().getInventory().getAwards();
		if(this instanceof River) {
			awardsCollected[0] = this.getAward();
		}else if(this instanceof Forest) {
			awardsCollected[1] = this.getAward();
		}else {
			System.out.println(this instanceof Cave);
			awardsCollected[2] = this.getAward();
		}
	}
	
	private void setSnakeDamageRandomly() {
		Random random = new Random();
		int damage = random.nextInt(4) + 3;
		this.getObstacle().setDamage(damage);
	}
	

}
