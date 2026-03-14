
import java.util.ArrayList;

public class CanAttend {

	//precondition: meetings is an ArrayList of MeetingInterval objects where each has startTime <= endTime and times are integers 0-2399
	//postcondition: returns true if no two meetings overlap, false if any two meetings have overlapping time intervals
	public static boolean canAttend(ArrayList<MeetingInterval> meetings) {                                                 
		// Check every pair of meetings for overlap
		for (int i = 0; i < meetings.size(); i++) {
			for (int j = i + 1; j < meetings.size(); j++) {
				MeetingInterval m1 = meetings.get(i);
				MeetingInterval m2 = meetings.get(j);
				// Two intervals overlap if start of one is between start and end of other and vice versa
				if ((m1.getStart() > m2.getStart() && m1.getStart() < m2.getEnd()            // s1 is between s2 and e2
                     || m2.getStart() > m1.getStart() && m2.getStart() < m1.getEnd())) {     // s2 is between s1 and e1
                    System.out.println("Conflict between meetings: " + m1 + " and " + m2);
					return false;
				}
			}
		}
		return true;
	}
	public static void main(String[] args) {
		ArrayList<MeetingInterval> meet = new ArrayList<MeetingInterval>();
		for(int i =0; i<10; i++) {
			int start = (int)(Math.random()*2399);
			int end = start + ((int)(Math.random()*(2399-start)));
			meet.add(new MeetingInterval(start, end));
		}
		System.out.println(canAttend(meet) ? "There is no conflict with attending "+meet+" meetings":"Can't attend "+meet+" meetings due to conflict");
		
		// Additional testing
		// Test 1: No meetings
		ArrayList<MeetingInterval> test1 = new ArrayList<>();
		System.out.println("No meetings: " + canAttend(test1) + ":" + test1); // true
		
		// Test 2: One meeting
		ArrayList<MeetingInterval> test2 = new ArrayList<>();
		test2.add(new MeetingInterval(100, 200));
		System.out.println("One meeting: " + canAttend(test2) + ":" + test2); // true
		
		// Test 3: Two non-overlapping meetings
		ArrayList<MeetingInterval> test3 = new ArrayList<>();
		test3.add(new MeetingInterval(0, 100));
		test3.add(new MeetingInterval(200, 300));
		System.out.println("Two non-overlapping: " + canAttend(test3) + ":" + test3); // true
		
		// Test 4: Two overlapping meetings
		ArrayList<MeetingInterval> test4 = new ArrayList<>();
		test4.add(new MeetingInterval(0, 200));
		test4.add(new MeetingInterval(100, 300));
		System.out.println("Two overlapping: " + canAttend(test4) + ":" + test4); // false
		
		// Test 5: Adjacent meetings (end of one equals start of next)
		ArrayList<MeetingInterval> test5 = new ArrayList<>();
		test5.add(new MeetingInterval(0, 100));
		test5.add(new MeetingInterval(100, 200));
		System.out.println("Adjacent meetings: " + canAttend(test5) + ":" + test5); // true
		
		// Test 6: Overlapping at boundary
		ArrayList<MeetingInterval> test6 = new ArrayList<>();
		test6.add(new MeetingInterval(0, 100));
		test6.add(new MeetingInterval(99, 200));
		System.out.println("Overlapping at boundary: " + canAttend(test6) + ":" + test6); // false
	}
}
