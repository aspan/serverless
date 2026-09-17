FROM public.ecr.aws/amazonlinux/amazonlinux:2023

RUN yum -y update \
    && yum install -y unzip tar gzip bzip2-devel ed gcc gcc-c++ gcc-gfortran \
    less libcurl-devel openssl openssl-devel readline-devel xz-devel \
    zlib-devel glibc-static zlib-static \
    && rm -rf /var/cache/yum

ENV GRAAL_FOLDERNAME=graalvm-community-25.3.4.1+1.1

RUN curl -4 -L https://github.com/graalvm/graalvm-ce-builds/releases/download/graal-25.3.4.1/graalvm-community-jdk-25i3-25.0.4.1_linux-aarch64_bin.tar.gz | tar -xvz
RUN mv $GRAAL_FOLDERNAME /usr/lib/graalvm
RUN rm -rf $GRAAL_FOLDERNAME

VOLUME /project
WORKDIR /project

ENV JAVA_HOME=/usr/lib/graalvm

WORKDIR /serverless
