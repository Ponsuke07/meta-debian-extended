# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=751419260aa954499f7abaabaa882bbe"

S = "${WORKDIR}/${BPN}-${PV}"

DEPENDS = "glib-2.0 intltool-native anthy ibus gobject-introspection \
swig swig-native python3-pygobject glib-2.0 glib-2.0-native gobject-introspection \
python3-dbus python3-pygobject-native iso-codes \
"
RDEPENDS_${PN} += " ibus anthy libanthy0"

PYTHON_BASEVERSION = "3.7"
PYTHON_PN = "python3"

inherit python3native pkgconfig gettext autotools gobject-introspection
inherit gtk-immodules-cache gtk-icon-cache
inherit debian-package
require recipes-debian/sources/ibus-anthy.inc

SRC_URI += " \
    file://0002-skip-ibuscheck-in-configure.patch \
    file://0003-remove-libanthy-from-makefile.patch \
    "

EXTRA_OECONF = "--with-python=python3"
UNKNOWN_CONFIGURE_WHITELIST = " --enable-introspection --disable-introspection"

do_configure_prepend() {
    touch ${S}/ChangeLog
    touch ${S}/ABOUT-NLS
    sed -i "s,^ANTHY_INCLUDEDIR .*$,ANTHY_INCLUDEDIR = ${STAGING_INCDIR}," ${S}/gir/Makefile.am
}

FILES_${PN} += "\
  /usr/share/glib-2.0/schemas/org.freedesktop.ibus.engine.anthy.gschema.xml \
  /usr/share/ibus/component/anthy.xml \
  /usr/share/metainfo/ibus-anthy.appdata.xml \
"
