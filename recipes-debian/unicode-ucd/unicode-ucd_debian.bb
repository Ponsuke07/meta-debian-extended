SUMMARY = "Unicode Character Database"
HOMEPAGE = "https://unicode.org/ucd/"
LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://debian/copyright;md5=3a259b806a5e190a2b24410bbcb8d380"

BPN = "unicode-data"
DEBIAN_QUILT_PATCHES = ""
DEPENDS = "unzip-native"

#SRC_URI = " \
#    https://www.unicode.org/Public/zipped/${PV}/UCD.zip;name=ucd;subdir=ucd \
#    https://www.unicode.org/license.html;name=license;subdir=${BP};downloadfilename=unicode.org.license.html \
#"
#SRC_URI[ucd.md5sum] = "430cbdac2615451571dd69a976dd08f6"
#SRC_URI[ucd.sha256sum] = "25ba51a0d4c6fa41047b7a5e5733068d4a734588f055f61e85f450097834a0a6"
#
#SRC_URI[license.md5sum] = "f03bafb623258f85ff2032c1ce567b7c"
#SRC_URI[license.sha256sum] = "983225207de8a707d0903a8d70fb7a4b28c5e0f64f2366e84a6192a2d618fed4"

inherit allarch
inherit debian-package
require recipes-debian/sources/unicode-data.inc

do_configure[noexec] = "1"

do_strip_pc() {
    #XXX: Wordarounds for do_debian_pach() error
    if [ -d ${DEBIAN_UNPACK_DIR}/.pc ]; then
        rm -rf ${DEBIAN_UNPACK_DIR}/.pc
    fi

    if [ -f ${DEBIAN_UNPACK_DIR}/debian/reiwa.patch ]; then
        rm ${DEBIAN_UNPACK_DIR}/debian/reiwa.patch
    fi
}
do_strip_pc[dirs] = "${DEBIAN_UNPACK_DIR}"
addtask strip_pc after do_debian_unpack_extra before do_debian_patch

do_install() {
    install -d ${D}${datadir}/unicode/ucd
    unzip ${S}/UCD.zip -d ${D}${datadir}/unicode/ucd
}

FILES_${PN} = "${datadir}/unicode/ucd"
