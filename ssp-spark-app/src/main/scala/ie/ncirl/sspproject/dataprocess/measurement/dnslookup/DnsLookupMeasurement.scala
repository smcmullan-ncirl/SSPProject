package ie.ncirl.sspproject.dataprocess.measurement.dnslookup

import ie.ncirl.sspproject.dataprocess.measurement.DeviceProperties

case class DnsLookupMeasurement
(
  task: Task,
  parameters: Parameters,
  success: String,
  timestamp: String,
  device_properties: DeviceProperties,
  values: Values,
  `type`: String,
  id: String
)

case class Task
(
  count: String,
  filter: String,
  parameters: TaskParameters,
  created: String,
  start_time: String,
  interval_sec: String,
  priority: String,
  tag: String,
  end_time: String,
  `type`: String,
  id: String
)

case class TaskParameters
(
  target: String,
  server: String,
  profile_1_freq: String,
  profile_2_freq: String,
  profile_3_freq: String,
  profile_4_freq: String,
  profile_unlimited: String
)

case class Parameters
(
  count: String,
  target: String,
  parameters: String,
  start_time: String,
  interval_sec: String,
  server: String,
  priority: String,
  end_time: String,
  key: String,
  `type`: String,
  context_interval_sec: String,
)

case class Values
(
  time_ms: String,
  CalledByLocation: String,
  MeasurementLatitude: String,
  real_hostname: String,
  address: String,
  MeasurementLongitude: String,
  error: String,
  context_results: String,
)

