package kaptainwutax.seedutils.lcg.rand;

import kaptainwutax.seedutils.lcg.LCG;

public class Rand {

    private final LCG lcg;
    private long seed;

    protected Rand(LCG lcg) {
        this.lcg = lcg;
    }

    protected Rand(LCG lcg, long seed) {
        this(lcg);
        this.setSeed(seed);
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public long getSeed() {
        return this.seed;
    }

    public long getBits(int bits) {
        this.seed = this.lcg.nextSeed(this.seed);

        if(this.lcg.isPowerOf2()) {
            return this.seed >>> (this.lcg.getTrailingZeroes() - bits);
        }

        throw new UnsupportedOperationException("lazy");
    }

    public void advance(long calls) {
        this.advance(this.lcg.combine(calls));
    }

    public void advance(LCG skip) {
        this.seed = skip.nextSeed(this.seed);
    }

    @Override
    public boolean equals(Object o) {
        if(this == o)return true;
        if(!(o instanceof Rand))return false;
        Rand rand = (Rand)o;
        return this.getSeed() == rand.getSeed() && this.lcg.equals(rand.lcg);
    }

    @Override
    public int hashCode() {
        return (int)(this.lcg.hashCode() + this.seed);
    }

    @Override
    public String toString() {
        return "Rand{" + "seed=" + this.seed + '}';
    }

}
