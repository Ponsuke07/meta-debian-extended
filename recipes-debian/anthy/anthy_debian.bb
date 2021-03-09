DESCRIPTION="Anthy is a system for Japanese input method. It converts Hiragana text to Kana Kanji mixed text."
AUTHOR = "Anthy Developers <anthy-dev@lists.sourceforge.jp>"
HOMEPAGE = "http://anthy.sourceforge.jp"

LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=f27defe1e96c2e1ecd4e0c9be8967949"

DEPENDS_class-target = "anthy-native"
RDEPENDS_${PN}_class-target = "libanthy0"

inherit autotools pkgconfig
inherit debian-package
require recipes-debian/sources/anthy.inc

SRC_URI += " \
    file://2ch_t.patch \
    file://fix-paths.patch \
"

SRC_URI_append_class-native = "file://native-helpers.patch"
SRC_URI_append_class-target = "file://target-helpers.patch"

PACKAGES += "${PN}-el libanthy0 libanthy-dev"

FILES_${PN}-dbg += "${libdir}/.debug"
FILES_libanthy0 = "${libdir}/libanthy.so.*  \
    ${libdir}/libanthydic.so.* \
    ${libdir}/libanthyinput.so.* \
"

FILES_libanthy-dev = "${libdir}/libanthy*.la \
    ${libdir}/libanthy*.a \
    ${libdir}/libanthy*.so \
    ${includedir}/anthy \
    ${libdir}/pkgconfig/anthy.pc \
"

FILES_${PN}-el = "${datadir}/emacs/*"
FILES_${PN} = "${datadir}/* \
    ${bindir}/* \
    ${sysconfdir}/anthy-conf \
"

BBCLASSEXTEND = "native"
