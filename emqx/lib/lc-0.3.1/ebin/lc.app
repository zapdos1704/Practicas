{application,lc,
             [{description,"load control app"},
              {vsn,"0.3.1"},
              {registered,[]},
              {mod,{lc_app,[]}},
              {applications,[kernel,stdlib]},
              {env,[]},
              {modules,[lc_app,lc_flag_man,lc_lib,lc_mem,lc_runq,lc_sup,
                        load_ctl]},
              {links,[{"Github","https://github.com/emqx/lc"}]},
              {maintainers,[]},
              {licenses,["Apache 2.0"]},
              {links,[]}]}.
