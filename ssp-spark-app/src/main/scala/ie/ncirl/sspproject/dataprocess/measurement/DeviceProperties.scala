package ie.ncirl.sspproject.dataprocess.measurement

case class DeviceProperties
(
  battery_level: Integer,
  cell_info: String,
  timestamp: String,
  network_type: String,
  os_version: String,
  device_info: DeviceInfo,
  carrier: String,
  location: Location,
  rssi: Integer,
  app_version: String,
  location_type: String,
  is_battery_charging: String,
  host_apps: Array[String],
  registration_id: String,
  mobilyzer_version: String,
  request_app: String,
  ssid: String,
  bssid: String,
  cell_rssi: String,
  wifi_ip_address: String,
  country_code: String
)

case class DeviceInfo
(
  model: String,
  os: String,
  manufacturer: String,
  tac: String,
  id: String,
  user: String
)

case class Location
(
  latitude: String,
  longitude: String
)
