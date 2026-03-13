public interface Schedulable {
    void schedule();
    void reschedule(String newDate, String newTime);
}