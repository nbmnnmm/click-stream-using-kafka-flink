mapping {
    map duplicate() onto 'detectedDuplicate'
    map corrupt() onto 'detectedCorruption'
    map firstInSession() onto 'firstInSession'
    map timestamp() onto 'timestamp'
    map remoteHost() onto 'remoteHost'
    map referer() onto 'referer'
    map location() onto 'location'
    def locationUri = parse location() to uri
    def localUri = parse locationUri.rawFragment() to uri
    map localUri.path() onto 'localPath'


    map viewportPixelWidth() onto 'viewportPixelWidth'
    map viewportPixelHeight() onto 'viewportPixelHeight'
    map screenPixelWidth() onto 'screenPixelWidth'
    map screenPixelHeight() onto 'screenPixelHeight'
    map partyId() onto 'partyId'
    map sessionId() onto 'sessionId'
    map pageViewId() onto 'pageViewId'
    map eventType() onto 'eventType'

    map userAgentString() onto 'userAgentString'
    def ua = userAgent()
    map ua.name() onto 'userAgentName'
    map ua.family() onto 'userAgentFamily'
    map ua.vendor() onto 'userAgentVendor'
    map ua.type() onto 'userAgentType'
    map ua.version() onto 'userAgentVersion'
    map ua.deviceCategory() onto 'userAgentDeviceCategory'
    map ua.osFamily() onto 'userAgentOsFamily'
    map ua.osVersion() onto 'userAgentOsVersion'
    map ua.osVendor() onto 'userAgentOsVendor'
}
