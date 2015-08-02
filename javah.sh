#!/bin/bash -e

TARGET_DIR="${HOME}/ClionProjects/libc_jni"

gradle clean build
cd build/classes/main

javah -d ${TARGET_DIR} net.nostromo.libc.Libc
javah -d ${TARGET_DIR} net.nostromo.libc.LibcConstantsJni
