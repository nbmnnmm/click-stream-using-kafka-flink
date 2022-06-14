mapping {
  map timestamp() onto 'timestamp'
  map remoteHost() onto 'remoteHost'
  map eventType() onto 'eventType'
  map location() onto 'location'

  def locationUri = parse location() to uri
  def localUri = parse locationUri.rawFragment() to uri
  map localUri.path() onto 'localPath'

  def localQuery = localUri.query()
  map localQuery.value('t') onto 't'
  map { parse localQuery.value('n') to int32 } onto 'n'
}