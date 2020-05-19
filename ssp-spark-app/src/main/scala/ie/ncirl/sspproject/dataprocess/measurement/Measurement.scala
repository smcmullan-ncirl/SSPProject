package ie.ncirl.sspproject.dataprocess.measurement

case class Measurement
(
  task: String,
  parameters: String,
  success: String,
  timestamp: String,
  device_properties: String,
  values: String,
  `type`: String,
  id: String
)