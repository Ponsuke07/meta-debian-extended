SUMMARY = "Library for sending desktop notifications to a notification daemon"
HOMEPAGE = "http://www.gnome.org"
SECTION = "libs"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=7fbc338309ac38fefcd64b04bb903e34"

DEPENDS = "dbus gtk+3 dbus-glib"

inherit gnomebase gtk-doc distro_features_check gobject-introspection
inherit debian-package
require recipes-debian/sources/libnotify.inc
# depends on gtk+3
ANY_OF_DISTRO_FEATURES = "${GTK3DISTROFEATURES}"

# there were times, we had two versions of libnotify (oe-core libnotify:0.6.x /
# meta-gnome libnotify3: 0.7.x)
PROVIDES += "libnotify3"
RPROVIDES_${PN} += "libnotify3"
RCONFLICTS_${PN} += "libnotify3"
RREPLACES_${PN} += "libnotify3"
