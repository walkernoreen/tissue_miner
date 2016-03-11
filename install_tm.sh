#!/usr/bin/env bash


## install dependencies
echo "Installing system dependencies..."
source ${TM_HOME}/misc/install_dependencies.sh | tee ${TM_HOME}/.tm_install_deps.log

## Install all required R packages (or run Setup.R in RStudio)
echo "Installing required R packages"
${TM_HOME}/Setup.R | tee ${TM_HOME}/.tm_install_rsetup.log

## compile the parser needed to convert TissueAnalyzer outputs into csv
echo "Installing parser to read TissueAnlyzer outputs"
cd ${TM_HOME}/parser && make | tee ${TM_HOME}/.tm_install_parser.log