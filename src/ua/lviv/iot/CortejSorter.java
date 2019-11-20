package ua.lviv.iot;

import java.util.*;

public class CortejSorter {
	private List<Cortej> cortejes = new ArrayList<>();
	private List<Cortej> sortedCortejes = new ArrayList<>();

	public CortejSorter(Cortej[] cortej) {
		for (Cortej c : cortej) {
			cortejes.add(c);
		}
	}

	public Cortej[] sort() {
		sortedCortejes.add(getMinCortej());
		sortList();
		cortejes.remove(getMinCortej());
		do {
			fillSortedCortej();
		} while (!cortejes.isEmpty());
		Cortej[] sortedArr = new Cortej[sortedCortejes.size()];
		for (int i = 0; i < sortedArr.length; i++) {
			sortedArr[i] = sortedCortejes.get(i);
		}
		return sortedArr;
	}

	private void sortList() {
		cortejes.sort(new Comparator<Cortej>() {
			@Override
			public int compare(Cortej o1, Cortej o2) {
				if (o1.getHour() < o2.getHour()) {
					return -1;
				} else if (o1.getHour() == o2.getHour()) {
					return 0;
				} else if (o1.getHour() > o2.getHour()) {
					return 1;
				}
				return 0;
			}
		});
	}

	private Cortej getMinCortej() {
		if (cortejes.isEmpty()) {
			System.err.println("Масив пустий!");
			return null;
		} else {
			long minTime = getMinTime();
			long maxDelta = 0;
			Cortej result = null;
			for (int i = 0; i < cortejes.size(); i++) {
				Cortej cortej = cortejes.get(i);
				if (cortej.getHour() == minTime) {
					long delta = cortej.getMinute() - cortej.getHour();
					if (delta > maxDelta) {
						result = cortej;
					}
				}
			}
			return result;
		}
	}

	private long getMinTime() {
		if (cortejes.isEmpty()) {
			return 0;
		} else {
			long min = cortejes.get(0).getHour();
			for (int i = 0; i < cortejes.size(); i++) {
				long tmp = cortejes.get(i).getHour();
				if (tmp < min) {
					min = tmp;
				}
			}
			return min;
		}
	}

	
	private void getMinHour(long hour, long minute,long localHourSort,long localMinuteSort,Iterator<Cortej> iterator,Cortej prevCortej,Cortej cortej) {
        if (minute < localMinuteSort) {
            iterator.remove();
            sortedCortejes.remove(prevCortej);
            sortedCortejes.add(new Cortej(hour, localMinuteSort));
        } else if (minute == localMinuteSort) {
            iterator.remove();
            sortedCortejes.remove(prevCortej);
            sortedCortejes.add(new Cortej(hour, localMinuteSort));
        } else if (minute > localMinuteSort) {
            sortedCortejes.add(cortej);
        }
	}
	
	private void getEqualHour(long hour, long minute,long localHourSort,long localMinuteSort,Iterator<Cortej> iterator,Cortej prevCortej,Cortej cortej) {
        if (minute < localMinuteSort) {
            iterator.remove();
        } else if (minute == localMinuteSort) {
            iterator.remove();
        } else if (minute > localMinuteSort) {
            iterator.remove();
            sortedCortejes.remove(prevCortej);
            sortedCortejes.add(new Cortej(localHourSort, minute));
        }
	}
	
	private void getZeroHour(long hour, long minute,long localHourSort,long localMinuteSort,Iterator<Cortej> iterator,Cortej prevCortej,Cortej cortej) {
        if (minute < localMinuteSort) {
            iterator.remove();
        } else if (minute == localMinuteSort) {
            iterator.remove();
        } else if (minute > localMinuteSort) {
            iterator.remove();
            sortedCortejes.remove(prevCortej);
            sortedCortejes.add(new Cortej(localHourSort, minute));
        }
	}
	
	
	private void fillSortedCortej() {
		if (cortejes.isEmpty() || sortedCortejes.isEmpty()) {
			return;
		}
		Cortej prevCortej = sortedCortejes.get(sortedCortejes.size() - 1);
		long localHourSort = prevCortej.getHour();
		long localMinuteSort = prevCortej.getMinute();
		Iterator<Cortej> iterator = cortejes.iterator();
		if (iterator.hasNext()) {
			Cortej cortej = iterator.next();
			long hour = cortej.getHour();
			long minute = cortej.getMinute();
			if (hour < localHourSort) {
			    getMinHour(hour,minute,localHourSort,localMinuteSort,iterator,prevCortej,cortej);
			} else if (localHourSort == hour) {
			    getEqualHour(hour,minute,localHourSort,localMinuteSort,iterator,prevCortej,cortej);
			} else if (hour > localHourSort && hour < localMinuteSort) {
			    getZeroHour(hour,minute,localHourSort,localMinuteSort,iterator,prevCortej,cortej);
			} else if (hour == localMinuteSort) {
				iterator.remove();
				sortedCortejes.remove(prevCortej);
				sortedCortejes.add(new Cortej(localHourSort, minute));
			} else {
				sortedCortejes.add(cortej);
				iterator.remove();
			}

		}
	}
}
