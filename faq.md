# TissueMiner FAQ


## How do I run snakemake with a project specific config file instead of `default_config.R`?


Export a shell variable pointing to the config file you'd like to use. E.g. to use `config/flywing_tm_config.R` just do
    
    export TM_CONFIG=$TM_HOME/config/flywing_tm_config.R

before running snakemake.