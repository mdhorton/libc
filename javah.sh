#!/bin/bash -e

TARGET_DIR="/home/mdhorton/ClionProjects/libc_jni"

gradle clean build
cd build/classes/main

su mdhorton -c "javah -d ${TARGET_DIR} net.nostromo.libc.Libc"
su mdhorton -c "javah -d ${TARGET_DIR} net.nostromo.libc.LibcConstantsJni"
