///////////////////////////////////////////////////////////////////////////////
//
// JTOpen (IBM Toolbox for Java - OSS version)
//
// Filename:  JobQueueListItem.java
//
// The source code contained herein is licensed under the IBM Public License
// Version 1.0, which has been approved by the Open Source Initiative.
// Copyright (C) 2018-2019 International Business Machines Corporation and
// others.  All rights reserved.
//
///////////////////////////////////////////////////////////////////////////////

package com.ibm.as400.access.list;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Information about a Job Queue generated by {@link com.ibm.as400.access.list.JobQueueOpenList JobQueueOpenList}.
 */
public class JobQueueListItem {
	public enum JobQueueStatus {
		/**
		 * The job queue is currently held. No jobs can become active from this job queue.
		 */
		HELD("0"),
		/**
		 * The job queue is released. Jobs can become active from this queue.
		 */
		RELEASED("1"),
		/**
		 * The job queue is damaged.
		 */
		DAMAGED("2"),
		/**
		 * The job queue is defined to the active subsystem, but has not been created.
		 * No jobs can become active from this job queue until it is created.
		 */
		DEFINED("3");

		private final String systemValue;

		//java8//private static Map<String, JobQueueStatus> valToEnum = Arrays.stream(JobQueueStatus.values()).collect(Collectors.toMap(JobQueueStatus::getSystemValue, Function.identity()));
		private static Map<String, JobQueueStatus> valToEnum = createMap();

		JobQueueStatus(String sysVal) {
			this.systemValue = sysVal;
		}

		private static Map<String, JobQueueStatus> createMap() {
			Map<String, JobQueueStatus> map = new HashMap<String, JobQueueStatus>();
			for (JobQueueStatus item : JobQueueStatus.values()) {
				map.put(item.getSystemValue(), item);
			}
			return Collections.unmodifiableMap(map);
		}

		public String getSystemValue() {
			return systemValue;
		}

		public static JobQueueStatus fromSystemValue(String systemValue) {
			return valToEnum.get(systemValue);
		}
	}

	/*package*/ String jobQueueName;
	/*package*/ String jobQueueLibrary;
	/*package*/ String jobQueueDescription;
	/*package*/ String subsystemName;
	/*package*/ String subsystemLibrary;
	/*package*/ int numberOfJobsWaitingToRun;
	/*package*/ int numberOfJobsRunning;
	/*package*/ int maximumActiveJobs;
	/*package*/ JobQueueStatus jobQueueStatus;
	/*package*/ int jobQueueSequence;
	/*package*/ String aspName;

	/*package*/ JobQueueListItem() {
	}

	/**
	 * The name of the job queue.
	 * @return The name of the job queue.
	 */
	public String getJobQueueName() {
		return jobQueueName;
	}

	/**
	 * The name of the library in which the job queue is located.
	 * @return The name of the library in which the job queue is located.
	 */
	public String getJobQueueLibrary() {
		return jobQueueLibrary;
	}

	/**
	 * The text description for this job queue.
	 * This field will be blank if the job queue is defined to an active subsystem,
	 * but has not been created or the job queue is damaged.
	 * @return The text description for this job queue.
	 */
	public String getJobQueueDescription() {
		return jobQueueDescription;
	}

	/**
	 * The name of the subsystem to which this job queue is allocated.
	 * If the job queue has been allocated by a different subsystem than was specified in the filter parameter,
	 * the subsystem name will identify the subsystem to which the job queue is allocated.
	 * This field is blank if the job queue is not allocated, is damaged, or does not exist.
	 * @return The name of the subsystem to which this job queue is allocated.
	 */
	public String getSubsystemName() {
		return subsystemName;
	}

	/**
	 * The library in which the subsystem description resides.
	 * This field will be blank if the job queue is not allocated, damaged or does not exist.
	 * @return The library in which the subsystem description resides.
	 */
	public String getSubsystemLibrary() {
		return subsystemLibrary;
	}

	/**
	 * The total number of jobs currently waiting to run on this job queue.
	 * This field is -1 if the job queue is defined to the active subsystem, but has not been created or the job queue is damaged.
	 * @return The total number of jobs currently waiting to run on this job queue.
	 */
	public int getNumberOfJobsWaitingToRun() {
		return numberOfJobsWaitingToRun;
	}

	/**
	 * The number of jobs currently running in the active subsystem from this job queue.
	 * This field is -1 if the job queue is not allocated, is damaged, does not exist, or the job queue has not
	 * been allocated by the subsystem that was specified in the active subsystem field in the filter parameter.
	 * @return The number of jobs currently running in the active subsystem from this job queue.
	 */
	public int getNumberOfJobsRunning() {
		return numberOfJobsRunning;
	}

	/**
	 * The maximum number of jobs that can be active in the subsystem from this job queue at one time.
	 * A -1 in this field indicates that the value is *NOMAX. This field is -2 if the job queue has not been
	 * defined to an active subsystem or the job queue is damaged.
	 * @return The maximum number of jobs that can be active in the subsystem from this job queue at one time.
	 */
	public int getMaximumActiveJobs() {
		return maximumActiveJobs;
	}

	/**
	 * The current status of the job queue.
	 * @return The current status of the job queue.
	 */
	public JobQueueStatus getJobQueueStatus() {
		return jobQueueStatus;
	}

	/**
	 * The job queue entry sequence number.
	 * The subsystem uses this number to determine the order in which the job queues are processed.
	 * Jobs from the job queue with the lowest sequence number in the job queue are selected first.
	 * This field is -1 if the job queue has not been defined to an active subsystem or the job queue is damaged.
	 * @return The job queue entry sequence number.
	 */
	public int getJobQueueSequence() {
		return jobQueueSequence;
	}

	/**
	 * The name of the auxiliary storage pool (ASP) device name where storage is allocated for the library
	 * that contains the object. The following special values may be returned:
	 * <ul>
	 * <li>*N - The name of the ASP device cannot be determined.</li>
	 * <li>*SYSBAS - System ASP (ASP 1) or basic user ASPs (ASPs 2-32).</li>
	 * </ul>
	 * @return The name of the auxiliary storage pool (ASP) device name where storage is allocated for the library
	 * that contains the object.
	 */
	public String getAspName() {
		return aspName;
	}
}