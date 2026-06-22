class Solution {
    public double angleClock(int hour, int minutes) {

        // 12 ko 0 maan lo
        if (hour == 12) {
            hour = 0;
        }

        double minuteAngle = minutes * 6.0;
        double hourAngle = hour * 30.0 + minutes * 0.5;
        double diff = Math.abs(hourAngle - minuteAngle);
        return Math.min(diff, 360 - diff);
    }
}