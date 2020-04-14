package ie.ncirl.diaproject.dataprocess.measurement.http

import ie.ncirl.diaproject.dataprocess.measurement.DeviceProperties

case class HttpMeasurement
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
  url: String,
  headers: String,
  method: String,
  profile_1_freq: String,
  profile_2_freq: String,
  profile_3_freq: String,
  profile_4_freq: String,
  profile_unlimited: String
)

case class Parameters
(
  body: String,
  count: String,
  parameters: String,
  url: String,
  start_time: String,
  interval_sec: String,
  priority: String,
  headers: String,
  end_time: String,
  key: String,
  `type`: String,
  method: String,
  context_interval_sec: String,
  sensitive: String,
  sample_byte_interval: String,
  resolved_ip: String
)

case class Values
(
  body: String,
  code: String,
  MeasurementLongitude: String,
  body_len: String,
  CalledByLocation: String,
  time_ms: Integer,
  MeasurementLatitude: String,
  headers: String,
  headers_len: String,
  error: String,
  MobilePktRecv: Integer,
  currentBatteryLevel: Integer,
  MobileBytesRecv: Integer,
  contextTimestamp: String,
  MobileBytesSend: Integer,
  currentRssi: Integer,
  MobilePktSend: Integer,
  contextMeasurementInterval: String,
  context_results: String,
  content_length: String,
  status_code: String,
  samples: String
)

