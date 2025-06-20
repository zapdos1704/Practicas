%%
%% %CopyrightBegin%
%% 
%% Copyright Ericsson AB 1996-2016. All Rights Reserved.
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

{application, os_mon,
   [{description, "CPO  CXC 138 46"},
    {vsn, "2.7.1"},
    {modules, [os_mon, os_mon_mib, os_sup,
               disksup, memsup, cpu_sup, os_mon_sysinfo, nteventlog]},
    {registered, [os_mon_sup, os_mon_sysinfo, disksup, memsup, cpu_sup, 
                  os_sup_server]},
    {applications, [kernel, stdlib, sasl]},
    {env, [{start_cpu_sup, true},
	   {start_disksup, true},
	   {start_memsup, true},
	   {start_os_sup, false}]},
    {mod, {os_mon, []}},
    {runtime_dependencies, ["stdlib-2.0","sasl-2.4","kernel-3.0","erts-6.0"]}]}.
