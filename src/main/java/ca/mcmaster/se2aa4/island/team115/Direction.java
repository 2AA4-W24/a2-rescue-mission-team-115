package ca.mcmaster.se2aa4.island.team115;

public enum Direction {
    N,
    E,
    S,
    W;

    public Direction leftDir() {
        switch(this) {
            case N: return W;
            case W: return S;
            case S: return E;
            case E: return N;
        }
        return this;
    }
    public  Direction rightDir(){
        switch(this) {
            case N: return E;
            case W: return N;
            case S: return W;
            case E: return S;
        }
        return this;
    }
}