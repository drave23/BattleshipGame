public class Statistic {

    int sunk;
    int hits;
    int total;

    public Statistic() {

    }

    public int getSunk() {
        return sunk;
    }

    public void setSunk(int sunk) {
        this.sunk = sunk;
    }

    public int getHits() {
        return hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    protected void updateStatistic(State state){
        total++ ;
        switch (state) {
            case PARTIAL_HIT:
                hits++;
                break;
            case SUNK:
                sunk++;
                hits++;
                break;
        }
    }
}
