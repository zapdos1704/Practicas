%%
%% %CopyrightBegin%
%% 
%% Copyright Ericsson AB 2002-2021. All Rights Reserved.
%% 
%% Licensed under the Apache License, Version 2.0 (the "License");
%% you may not use this file except in compliance with the License.
%% You may obtain a copy of the License at
%%
%%     http://www.apache.org/licenses/LICENSE-2.0
%%
%% Unless required by applicable law or agreed to in writing, software
%% distributed under the License is distributed on an "AS IS" BASIS,
%% WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
%% See the License for the specific language governing permissions and
%% limitations under the License.
%% 
%% %CopyrightEnd%
%%
{application, observer,
   [{description, "OBSERVER version 1"},
    {vsn, "2.10.1"},
    {modules, [crashdump_viewer,
	       cdv_atom_cb,
	       cdv_bin_cb,
	       cdv_detail_wx,
	       cdv_dist_cb,
	       cdv_ets_cb,
	       cdv_fun_cb,
	       cdv_gen_cb,
	       cdv_html_wx,
	       cdv_info_wx,
	       cdv_int_tab_cb,
	       cdv_mem_cb,
	       cdv_mod_cb,
	       cdv_multi_wx,
               cdv_persistent_cb,
	       cdv_port_cb,
	       cdv_proc_cb,
	       cdv_table_wx,
	       cdv_term_cb,
	       cdv_sched_cb,
	       cdv_timer_cb,
	       cdv_virtual_list_wx,
	       cdv_wx,
	       etop,
	       etop_tr,
	       etop_txt,
	       observer,
	       observer_alloc_wx,
	       observer_app_wx,
	       observer_html_lib,
	       observer_lib,
	       observer_perf_wx,
	       observer_port_wx,
	       observer_pro_wx,
	       observer_procinfo,
	       observer_sock_wx,
	       observer_sys_wx,
	       observer_trace_wx,
	       observer_traceoptions_wx,
	       observer_tv_table,
	       observer_tv_wx,
	       observer_wx,
	       ttb,
	       ttb_et]},
    {registered, []},
    {applications, [kernel, stdlib]},
    {env, []},
    {runtime_dependencies, ["wx-1.2","stdlib-3.13","runtime_tools-1.17",
			    "kernel-8.1","et-1.5","erts-11.0"]}]}.


