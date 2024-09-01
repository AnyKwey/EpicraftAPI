//@EventHandler
//  public void onExplosion(EntityExplodeEvent event) {
//    Location loc = event.getLocation();
//    List<Block> destroyed = event.blockList();
//    Iterator<Block> it = destroyed.iterator();
//    while (it.hasNext()) {
//      Block block = it.next();
//      Location bloc = block.getLocation();
//      Material mat = block.getType();
//      double x = 0.0D;
//      double y = 0.0D;
//      double z = 0.0D;
//      x = loc.getX() - bloc.getX();
//      y = loc.getY() - bloc.getY();
//      z = loc.getZ() - bloc.getZ();
//      x = -x * 0.3D * Math.random();
//      y = y * 0.5D * Math.random() + 0.5D;
//      z = -z * 0.3D * Math.random();
//      FallingBlock fallingb = bloc.getWorld().spawnFallingBlock(bloc, mat, (byte)0);
//      fallingb.setVelocity(new Vector(x, y, z));
//    }

//@EventHandler
//  public void onHit(ProjectileHitEvent event) {
//    if (event.getEntity() instanceof org.bukkit.entity.Arrow &&
//      event.getEntity().getShooter() instanceof Player) {
//      Projectile projectile = event.getEntity();
//      BlockIterator iterator = new BlockIterator(projectile.getWorld(), projectile.getLocation().toVector(), projectile.getVelocity().normalize(), 0.0D, 4);
//      Block hitBlock = null;
//      while (iterator.hasNext()) {
//        hitBlock = iterator.next();
//        if (hitBlock.getTypeId() != 0)
//          break;
//      }
//      LivingEntity liv = event.getEntity().getShooter();
//      Player p = (Player)liv;
//      if (p.getInventory().getItemInHand().getType().equals(Material.BOW) &&
//        p.hasPermission("arrowtp.use")) {
//        int tpX = (int)hitBlock.getLocation().getX();
//        int tpY = (int)hitBlock.getLocation().getY();
//        int tpZ = (int)hitBlock.getLocation().getZ();
//        int tpyaw = (int)p.getLocation().getYaw();
//        int tppitch = (int)p.getLocation().getPitch();
//        Location loc = new Location(p.getWorld(), tpX, tpY, tpZ, tpyaw, tppitch);
//        p.teleport(loc.add(0.0D, 2.0D, 0.0D));
//        p.playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 1000);
//        p.playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 1000);
//        p.playEffect(p.getLocation(), Effect.SMOKE, 1000);
//        p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 100000.0F, 100000.0F);
//        p.playSound(p.getLocation(), Sound.BLAZE_HIT, 100000.0F, 100000.0F);
//      }
//    }
//  }