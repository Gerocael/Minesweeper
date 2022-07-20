package minesweeper;

import java.util.*;

/**
 * Player times.
 */

public class BestTimes implements Iterable<BestTimes.PlayerTime> {
    /** List of best player times. */
    private List<PlayerTime> playerTimes = new ArrayList<PlayerTime>();

    /**
     * Returns an iterator over a set of  best times.
     * @return an iterator
     */
    public Iterator<PlayerTime> iterator() {
        return playerTimes.iterator();
    }

    /**
     * Adds player time to best times.
     * @param name name ot the player
     * @param time player time in seconds
     */
    public void addPlayerTime(String name, int time) {
        PlayerTime player = new PlayerTime(name, time);
        playerTimes.add(player);
        Collections.sort(playerTimes);
    }

    /**
     * Returns a string representation of the object.
     * @return a string representation of the object
     */
    @Override
    public String toString() {
        Formatter f = new Formatter();
        for (int i = 0; i < playerTimes.size(); i++) {
            System.out.printf("%d %s %d", i, playerTimes.get(i).name, playerTimes.get(i).time);
        }
        return f.toString();
    }

    /**
     * Player time.
     */
    public static class PlayerTime implements Comparable<PlayerTime>{
        /** Player name. */
        private final String name;

        /** Playing time in seconds. */
        private final int time;

        private BestTimes bestTimes;

        private static  Minesweeper instance;


        /**
         * Constructor.
         * @param name player name
         * @param time playing game time in seconds
         */

        public PlayerTime(String name, int time) {
            this.name = name;
            this.time = time;
        }

        public int compareTo(PlayerTime o){
            return time - o.time;
        }

        public BestTimes getBestTimes() {
            return bestTimes;
        }

    }