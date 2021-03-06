# base recipe: meta/recipes-devtools/ruby/ruby_2.5.5.bb
# base branch: zeus
# base commit: 94f6b31befda5c496f65e863a6f8152b42d7ebf0

SUMMARY = "An interpreter of object-oriented scripting language"
DESCRIPTION = "Ruby is an interpreted scripting language for quick \
and easy object-oriented programming. It has many features to process \
text files and to do system management tasks (as in Perl). \
It is simple, straight-forward, and extensible. \
"
HOMEPAGE = "http://www.ruby-lang.org/"
SECTION = "devel/ruby"
LICENSE = "Ruby | BSD | GPLv2"
LIC_FILES_CHKSUM = "\
    file://COPYING;md5=340948e1882e579731841bf49cdc22c1 \
    file://BSDL;md5=19aaf65c88a40b508d17ae4be539c4b5\
    file://GPL;md5=b234ee4d69f5fce4486a80fdaf4a4263\
    file://LEGAL;md5=23a79bb4c1a40f6cc9bcb6f4e7c39799 \
"

DEPENDS = "ruby-native zlib openssl tcl libyaml gdbm readline libffi"
DEPENDS_class-native = "openssl-native libyaml-native readline-native zlib-native"

SHRT_VER = "${@oe.utils.trim_version("${PV}", 2)}"

FILESPATH_append = ":${THISDIR}/ruby:"
SRC_URI += " \
            file://extmk.patch \
            file://0002-Obey-LDFLAGS-for-the-link-of-libruby.patch \
           "

inherit autotools ptest

# This snippet lets compiled extensions which rely on external libraries,
# such as zlib, compile properly.  If we don't do this, then when extmk.rb
# runs, it uses the native libraries instead of the target libraries, and so
# none of the linking operations succeed -- which makes extconf.rb think
# that the libraries aren't available and hence that the extension can't be
# built.

do_configure_prepend() {
    sed -i "s#%%TARGET_CFLAGS%%#$TARGET_CFLAGS#; s#%%TARGET_LDFLAGS%%#$TARGET_LDFLAGS#" ${S}/common.mk
    rm -rf ${S}/ruby/
}
