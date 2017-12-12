#!/bin/sh
# DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
#
# Copyright (c) 2016 Oracle and/or its affiliates. All rights reserved.
#
# Oracle and Java are registered trademarks of Oracle and/or its affiliates.
# Other names may be trademarks of their respective owners.
#
# The contents of this file are subject to the terms of either the GNU
# General Public License Version 2 only ("GPL") or the Common
# Development and Distribution License("CDDL") (collectively, the
# "License"). You may not use this file except in compliance with the
# License. You can obtain a copy of the License at
# http://www.netbeans.org/cddl-gplv2.html
# or nbbuild/licenses/CDDL-GPL-2-CP. See the License for the
# specific language governing permissions and limitations under the
# License.  When distributing the software, include this License Header
# Notice in each file and include the License file at
# nbbuild/licenses/CDDL-GPL-2-CP.  Oracle designates this
# particular file as subject to the "Classpath" exception as provided
# by Oracle in the GPL Version 2 section of the License file that
# accompanied this code. If applicable, add the following below the
# License Header, with the fields enclosed by brackets [] replaced by
# your own identifying information:
# "Portions Copyrighted [year] [name of copyright owner]"
#
# If you wish your version of this file to be governed by only the CDDL
# or only the GPL Version 2, indicate your decision by adding
# "[Contributor] elects to include this software in this distribution
# under the [CDDL or GPL Version 2] license." If you do not indicate a
# single choice of license, a recipient has the option to distribute
# your version of this file under either the CDDL, the GPL Version 2 or
# to extend the choice of license to its licensees as provided above.
# However, if you add GPL Version 2 code and therefore, elected the GPL
# Version 2 license, then the option applies only if the new code is
# made subject to such option by the copyright holder.
#
# Contributor(s):

PATH=/usr/bin:/bin:/usr/sbin:/sbin:/usr/local/bin
HOSTNAME=`uname -n`
OS=`uname -s`
CPUTYPE=`uname -p`
BITNESS=32

LS=/bin/ls
OSFAMILY=
DATETIME=`date -u +'%Y-%m-%d %H:%M:%S'`

if [ "${OS}" = "SunOS" ]; then
   BITNESS=`isainfo -b`
   OSFAMILY="SUNOS"
   OSNAME="SunOS"
   OSBUILD=`head -1 /etc/release | sed -e "s/^ *//"`
   CPUNUM=`/usr/sbin/psrinfo -v | grep "^Status of" | wc -l | sed 's/^ *//'`
else
   if [ "${OS}" = "Darwin" ]; then
      sysctl hw.cpu64bit_capable | grep -q "1$"
      if [ $? -eq 0 ]; then
         BITNESS=64
      fi
   else
      uname -a | egrep "x86_64|WOW64|sparc64" >/dev/null
      if [ $? -eq 0 ]; then
         BITNESS=64
      fi
   fi

   if [ -f "/etc/sun-release" ]; then
      OSNAME="${OS}-JDS"
      OSBUILD=`head -1 /etc/sun-release`
   elif [ -f /etc/SuSE-release ]; then
      OSNAME="${OS}-SuSE"
      OSBUILD=`cat /etc/SuSE-release | tr "\n" " "`;
   elif [ -f /etc/redhat-release ]; then
      OSNAME="${OS}-Redhat"
      OSBUILD=`head -1 /etc/redhat-release`
   elif [ -f /etc/gentoo-release ]; then
      OSNAME="${OS}-Gentoo"
      OSBUILD=`head -1 /etc/gentoo-release`
   elif [ -f /etc/lsb-release ]; then
      OSNAME="${OS}-"`cat /etc/lsb-release | grep DISTRIB_ID | sed 's/.*=//'`
      OSBUILD=`cat /etc/lsb-release | grep DISTRIB_DESCRIPTION | sed 's/.*=//' | sed 's/"//g'`
   fi
fi

OSFAMILY=${OSFAMILY:-`echo ${OS} | grep _NT- >/dev/null && echo WINDOWS`}
OSFAMILY=${OSFAMILY:-`test "$OS" = "Darwin" && echo MACOSX`}
OSFAMILY=${OSFAMILY:-`test "$OS" = "Linux" && echo LINUX`}
OSFAMILY=${OSFAMILY:-${OS}}

CPUFAMILY=`(echo ${CPUTYPE} | egrep "^i|x86_64|athlon|Intel" >/dev/null && echo x86) || echo ${CPUTYPE}`
if [ "${CPUFAMILY}" != "x86" -a "${CPUFAMILY}" != "sparc" -a "${CPUFAMILY}" != "sparc64" ]; then
   CPUTYPE=`uname -m`
fi
CPUFAMILY=`(echo ${CPUTYPE} | egrep "^i|x86_64|athlon|Intel" >/dev/null && echo x86) || echo ${CPUTYPE}`
if [ "${CPUFAMILY}" = "sparc64" ]; then
   CPUFAMILY="sparc"
fi

USERDIRBASE=${HOME}

if [ "${OSFAMILY}" = "LINUX" ]; then
   if [ "${CPUFAMILY}" = "sparc" ]; then
     CPUNUM=`cat /proc/cpuinfo | grep 'ncpus active' | sed 's/[^:]*.[ ]*//'`
   else
     CPUNUM=`cat /proc/cpuinfo | grep processor | wc -l | sed 's/^ *//'`
   fi
elif [ "${OSFAMILY}" = "WINDOWS" ]; then
   CPUNUM=$NUMBER_OF_PROCESSORS
   OSNAME=`uname`
   USERDIRBASE=${USERPROFILE}
elif [ "${OSFAMILY}" = "MACOSX" ]; then
   CPUNUM=`hostinfo | awk '/processor.*logical/{print $1}'`
   OSNAME="MacOSX"
   OSBUILD=`hostinfo | sed -n '/kernel version/{n;p;}' | sed 's/[	 ]*\([^:]*\).*/\1/'`
elif [ "${OSFAMILY}" = "FreeBSD" ]; then
   CPUNUM=`sysctl hw.ncpu | awk '{print $2}'`
   OSNAME=`sysctl -n  kern.ostype`
   OSBUILD=`sysctl -n kern.osrelease`
fi

wx_fail() {
    tmp="${1}/wx_test"
    touch ${tmp} 2> /dev/null
    if [ $? -eq 0 ]; then
        chmod u+x ${tmp} 2> /dev/null
        if [ -x ${tmp} ]; then
            rm ${tmp} 2> /dev/null
            return 1
        fi
        rm ${tmp} 2> /dev/null
    fi

    return 0
}

USER=${USER:-`logname 2>/dev/null`}
USER=${USER:-${USERNAME}}
TMPBASE=${TMPBASE:-/var/tmp}

SUFFIX=0
TMPDIRBASE=${TMPBASE}/dlight_${USER}

if wx_fail ${TMPBASE}; then
    if wx_fail ${TMPDIRBASE}; then
        TMPBASE=/tmp
        TMPDIRBASE=${TMPBASE}/dlight_${USER}
    fi
fi

mkdir -p ${TMPDIRBASE}
while [ ${SUFFIX} -lt 5 ]; do
    if wx_fail ${TMPDIRBASE}; then
        echo "Warning: TMPDIRBASE is not writable: ${TMPDIRBASE}">&2
        SUFFIX=`expr 1 + ${SUFFIX}`
        TMPDIRBASE=${TMPBASE}/dlight_${USER}_${SUFFIX}
        /bin/mkdir -p ${TMPDIRBASE} 2>/dev/null
    else
        break
    fi
done

if wx_fail ${TMPDIRBASE}; then
    :
else
    SUFFIX=0
    TMPBASE=${TMPDIRBASE}
    TMPDIRBASE=${TMPBASE}/${NB_KEY}
    mkdir -p ${TMPDIRBASE}
    while [ ${SUFFIX} -lt 5 ]; do
        if wx_fail ${TMPDIRBASE}; then
            echo "Warning: TMPDIRBASE is not writable: ${TMPDIRBASE}">&2
            SUFFIX=`expr 1 + ${SUFFIX}`
            TMPDIRBASE=${TMPBASE}/${NB_KEY}_${SUFFIX}
            /bin/mkdir -p ${TMPDIRBASE} 2>/dev/null
        else
            break
        fi
    done
fi

if wx_fail ${TMPDIRBASE}; then
    TMPDIRBASE=${TMPBASE}
fi

if wx_fail ${TMPDIRBASE}; then
    echo "Error: TMPDIRBASE is not writable: ${TMPDIRBASE}">&2
fi

ENVFILE="${TMPDIRBASE}/env"

ID=`LC_MESSAGES=C /usr/bin/id`

echo BITNESS=${BITNESS}
echo CPUFAMILY=${CPUFAMILY}
echo CPUNUM=${CPUNUM}
echo CPUTYPE=${CPUTYPE}
echo HOSTNAME=${HOSTNAME}
echo OSNAME=${OSNAME}
echo OSBUILD=${OSBUILD}
echo OSFAMILY=${OSFAMILY}
echo USER=${USER}
echo SH=${SHELL}
echo USERDIRBASE=${USERDIRBASE}
echo TMPDIRBASE=${TMPDIRBASE}
echo DATETIME=${DATETIME}
echo ENVFILE=${ENVFILE}
echo ID=${ID}
exit 0
