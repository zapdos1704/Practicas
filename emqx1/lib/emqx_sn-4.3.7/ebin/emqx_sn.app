{application,emqx_sn,
             [{description,"EMQ X MQTT-SN Plugin"},
              {vsn,"4.3.7"},
              {modules,[emqx_sn_app,emqx_sn_asleep_timer,emqx_sn_broadcast,
                        emqx_sn_frame,emqx_sn_gateway,emqx_sn_registry,
                        emqx_sn_sup]},
              {registered,[]},
              {applications,[kernel,stdlib,esockd]},
              {mod,{emqx_sn_app,[]}},
              {env,[]},
              {licenses,["Apache-2.0"]},
              {maintainers,["EMQ X Team <contact@emqx.io>"]},
              {links,[{"Homepage","https://emqx.io/"},
                      {"Github","https://github.com/emqx/emqx-sn"}]}]}.
