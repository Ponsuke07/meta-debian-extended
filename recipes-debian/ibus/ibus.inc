# base recipe: meta-openembedded/meta-oe/recipes-support/ibus/ibus.inc
# base branch: master
# base commit: 98175fd0cc6eefe4db9484131bb98f0516c0d1a5

SUMMARY = "Intelligent Input Bus for Linux/Unix"
HOMEPAGE = "https://github.com/ibus/ibus/wiki"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=fbc093901857fcd118f065f900982c24"

inherit autotools-brokensep pkgconfig gobject-introspection gettext gtk-doc vala
inherit debian-package
require recipes-debian/sources/ibus.inc

DEPENDS = "unicode-ucd intltool-native python3-six-native"

SRC_URI += " \
    file://Do-not-try-to-start-dbus.patch \
    file://0001-Delete-Super-space-notification.patch \
"
S = "${WORKDIR}/ibus-${PV}"

PACKAGECONFIG[appindicator] = "--enable-appindicator,--disable-appindicator,qtbase"
PACKAGECONFIG[dconf] = "--enable-dconf,--disable-dconf,dconf"
PACKAGECONFIG[gtk2] = "--enable-gtk2,--disable-gtk2,gtk+"
PACKAGECONFIG[gtk3] = "--enable-gtk3,--disable-gtk3,gtk+3"
PACKAGECONFIG[wayland]  = "--enable-wayland,--disable-wayland,wayland"
PACKAGECONFIG[vala]  = "--enable-vala,--disable-vala"
PACKAGECONFIG[x11]  = "--enable-xim --enable-ui,--disable-xim --disable-ui,virtual/libx11"

EXTRA_OECONF = " \
    --disable-tests \
    --disable-emoji-dict \
    --disable-python2 \
"

do_configure_prepend() {
    touch ${S}/ChangeLog
    touch ${S}/ABOUT-NLS
}
