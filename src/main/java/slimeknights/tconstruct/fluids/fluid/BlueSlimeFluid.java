package slimeknights.tconstruct.fluids.fluid;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.IFluidState;
import net.minecraft.state.StateContainer;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public abstract class BlueSlimeFluid extends ForgeFlowingFluid {

  protected BlueSlimeFluid(Properties properties) {
    super(properties);
  }

  /*@Override
  TODO: FIX
  public void randomTick(World world, BlockPos pos, IFluidState state, Random random) {
    int oldLevel = getLevelFromState(state);
    super.randomTick(world, pos, state, random);

    if (oldLevel > 0 && oldLevel == getLevelFromState(state)) {
      if (random.nextFloat() > 0.6f) {
        // only if they have dirt below them
        Block blockDown = world.getBlockState(pos.down()).getBlock();
        if (blockDown == Blocks.DIRT) {
          // check if the block we flowed from has slimedirt below it and move the slime with us!
          for (Direction dir : Direction.Plane.HORIZONTAL) {
            IFluidState state2 = world.getFluidState(pos.offset(dir));
            // same block and a higher flow
            if (state2.getFluid() == this && getLevelFromState(state2) == getLevelFromState(state) - 1) {
              BlockState dirt = world.getBlockState(pos.offset(dir).down());
              if (dirt.getBlock() == TinkerWorld.green_slime_dirt || dirt.getBlock() == TinkerWorld.blue_slime_dirt || dirt.getBlock() == TinkerWorld.purple_slime_dirt || dirt.getBlock() == TinkerWorld.magma_slime_dirt) {
                // we got a block we flowed from and the block we flowed from has slimedirt below
                // change the dirt below us to slimedirt too
                world.setBlockState(pos.down(), dirt);
              }
              if (dirt.getBlock() == TinkerWorld.blue_green_slime_grass) {
                world.setBlockState(pos.down(), TinkerWorld.blue_green_slime_grass.getDirtState(dirt));
              }
            }
          }
        }
      }

      //world.scheduleBlockUpdate(pos, this, 400 + rand.nextInt(200), 0);
    }
  }*/

  public static class Flowing extends BlueSlimeFluid {

    public Flowing(Properties properties) {
      super(properties);
      this.setDefaultState(this.getStateContainer().getBaseState().with(LEVEL_1_8, 7));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Fluid, IFluidState> builder) {
      super.fillStateContainer(builder);
      builder.add(LEVEL_1_8);
    }

    @Override
    public int getLevel(IFluidState state) {
      return state.get(LEVEL_1_8);
    }

    @Override
    public boolean isSource(IFluidState state) {
      return false;
    }
  }

  public static class Source extends BlueSlimeFluid {

    public Source(Properties properties) {
      super(properties);
    }

    @Override
    public int getLevel(IFluidState state) {
      return 8;
    }

    @Override
    public boolean isSource(IFluidState state) {
      return true;
    }
  }
}