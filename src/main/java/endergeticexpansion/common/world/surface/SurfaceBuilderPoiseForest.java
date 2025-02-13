package endergeticexpansion.common.world.surface;

import java.util.Random;
import java.util.function.Function;

import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class SurfaceBuilderPoiseForest extends SurfaceBuilder<SurfaceBuilderConfig> {
	
	public SurfaceBuilderPoiseForest(Function<Dynamic<?>, ? extends SurfaceBuilderConfig> config) {
		super(config);
	}

	public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
		if (noise > 3.55D) {
			SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, EESurfaceBuilders.POISMOSS_CONFIG);
		} else if (noise > 3.1D) {
			SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, EESurfaceBuilders.EUMUS_CONFIG);
		} else {
			SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, EESurfaceBuilders.END_STONE_CONFIG);
		}
	}

}
