const _0x34e79c=_0x5ade;(function(_0x13e891,_0x3fa0fc){const _0xb37542=_0x5ade,_0x491eb1=_0x13e891();while(!![]){try{const _0x3e854d=parseInt(_0xb37542(0xa5))/0x1+-parseInt(_0xb37542(0xae))/0x2*(parseInt(_0xb37542(0xb0))/0x3)+-parseInt(_0xb37542(0x9b))/0x4*(-parseInt(_0xb37542(0xa2))/0x5)+-parseInt(_0xb37542(0xaf))/0x6*(-parseInt(_0xb37542(0x98))/0x7)+parseInt(_0xb37542(0xbc))/0x8+parseInt(_0xb37542(0xa4))/0x9+-parseInt(_0xb37542(0xa0))/0xa*(parseInt(_0xb37542(0xb2))/0xb);if(_0x3e854d===_0x3fa0fc)break;else _0x491eb1['push'](_0x491eb1['shift']());}catch(_0x3aeac3){_0x491eb1['push'](_0x491eb1['shift']());}}}(_0x1d0a,0x37f01));function _0x1d0a(){const _0x10c263=['code','value','update','queryById','280UWjuku','queryByFilter','5mayKit','substring','2840535HvUVen','300114vvVyYD','length','../errors/errors','toLowerCase','where','getId','SQLITE_ERROR:\x20','build','deleteByFilter','106AKgyDf','438IFiFTM','9120oTCQxC','LOWER(','330462gAEnet','delete','count(*)','clone','../utils/PagedResult','offset','exports','message','tableName','handleConstraintError','521808XOtyxe','limit','name','SQLITE_CONSTRAINT:\x20',')\x20LIKE\x20?','search','37625NvGJeX','handleError','insert','632348mYkAfM'];_0x1d0a=function(){return _0x10c263;};return _0x1d0a();}function _0x5ade(_0x1194e7,_0x47596a){const _0x1d0ac7=_0x1d0a();return _0x5ade=function(_0x5ade78,_0x4d076b){_0x5ade78=_0x5ade78-0x97;let _0x35578e=_0x1d0ac7[_0x5ade78];return _0x35578e;},_0x5ade(_0x1194e7,_0x47596a);}const {DataConstraintError,DatabaseError}=require(_0x34e79c(0xa7)),{PagedResult}=require(_0x34e79c(0xb6));module[_0x34e79c(0xb8)]=class Repository{constructor(_0x156fad,_0x5488ba){const _0x5e2e6d=_0x34e79c;this['db']=_0x156fad,this[_0x5e2e6d(0xba)]=_0x5488ba;}['getId'](_0x102039){return{'id':_0x102039['id']};}['setId'](_0x3a3f3d,_0x26d770){_0x26d770['id']=_0x3a3f3d[0x0];}async[_0x34e79c(0x9a)](_0x5378ad){const _0x3114d3=_0x34e79c;try{const _0x297f1e=await this['db'](this[_0x3114d3(0xba)])[_0x3114d3(0x9a)](_0x5378ad);if(!_0x297f1e)return undefined;return this['setId'](_0x297f1e,_0x5378ad),_0x5378ad;}catch(_0x55cad7){throw this[_0x3114d3(0xbb)](_0x55cad7);}}async[_0x34e79c(0x9e)](_0x1835e5){const _0x5da282=_0x34e79c;try{const _0x521716=await this['db'](this[_0x5da282(0xba)])[_0x5da282(0xa9)](this[_0x5da282(0xaa)](_0x1835e5))[_0x5da282(0x9e)](_0x1835e5);if(_0x521716===0x0)return undefined;return _0x1835e5;}catch(_0x239172){throw this[_0x5da282(0xbb)](_0x239172);}}async['deleteById'](_0x11d9ec){const _0x129a41=_0x34e79c;return await this[_0x129a41(0xad)]({'id':_0x11d9ec});}async['deleteByFilter'](_0x5f22de){const _0x1c2885=_0x34e79c;try{return await this['db'](this[_0x1c2885(0xba)])[_0x1c2885(0xa9)](_0x5f22de)[_0x1c2885(0xb3)]();}catch(_0x3c2fa2){throw this[_0x1c2885(0x99)](_0x3c2fa2);}}async[_0x34e79c(0x9f)](_0x1c66fa){return await this['queryByFilter']({'id':_0x1c66fa});}async[_0x34e79c(0xa1)](_0x24e78f){const _0x466bb7=_0x34e79c;try{const _0x8faa43=await this['db'](this[_0x466bb7(0xba)])['where'](_0x24e78f);if(_0x8faa43[_0x466bb7(0xa6)]===0x0)return undefined;return _0x8faa43[0x0];}catch(_0x1e1db7){throw this[_0x466bb7(0x99)](_0x1e1db7);}}async['queryByAdvancedFilter'](_0x47dff9,_0x2bae5f={'column':_0x34e79c(0xbe),'value':''},_0x493af7,_0x3b1afb,_0x4c871a,_0x2d3934){const _0x478253=_0x34e79c;try{let _0x1c6dc6=this['db'](this['tableName']);if(_0x47dff9)_0x1c6dc6[_0x478253(0xa9)](_0x47dff9);if(_0x2bae5f)_0x1c6dc6['andWhereRaw'](_0x478253(0xb1)+_0x2bae5f['column']+_0x478253(0xc0),'%'+_0x2bae5f[_0x478253(0x9d)][_0x478253(0xa8)]()+'%');if(_0x4c871a&&_0x2d3934)_0x1c6dc6['orderBy'](_0x4c871a,_0x2d3934);const _0x51b0ba=PagedResult[_0x478253(0xbd)](_0x3b1afb),_0xe3025d=PagedResult[_0x478253(0xb7)](_0x493af7,_0x51b0ba),_0x242fc2=await _0x1c6dc6[_0x478253(0xb5)]()[_0x478253(0xb7)](_0xe3025d)[_0x478253(0xbd)](_0x51b0ba),_0xf6cced=await _0x1c6dc6[_0x478253(0xb5)]()['count']();return PagedResult[_0x478253(0xac)]({'totalCount':_0xf6cced[0x0][_0x478253(0xb4)],'offset':_0xe3025d,'limit':_0x51b0ba,'orderBy':_0x4c871a,'direction':_0x2d3934,'content':_0x242fc2});}catch(_0x16b1bf){throw this[_0x478253(0x99)](_0x16b1bf);}}[_0x34e79c(0x99)](_0x4de6b9){const _0x3f27a1=_0x34e79c;if(_0x4de6b9[_0x3f27a1(0x9c)]==='SQLITE_ERROR'){let _0x4a59ff=_0x4de6b9[_0x3f27a1(0xb9)];const _0x2b569f=_0x3f27a1(0xab),_0x339429=_0x4a59ff['search'](_0x2b569f)+_0x2b569f[_0x3f27a1(0xa6)];return _0x4a59ff=_0x4a59ff[_0x3f27a1(0xa3)](_0x339429,_0x4a59ff[_0x3f27a1(0xa6)]),new DatabaseError([_0x4a59ff]);}else return _0x4de6b9;}[_0x34e79c(0xbb)](_0x3dbd11){const _0x280bc2=_0x34e79c;if(_0x3dbd11[_0x280bc2(0x9c)]==='SQLITE_CONSTRAINT'){let _0x1c005a=_0x3dbd11[_0x280bc2(0xb9)];const _0x350530=_0x280bc2(0xbf),_0x383977=_0x1c005a[_0x280bc2(0x97)](_0x350530)+_0x350530['length'];return _0x1c005a=_0x1c005a[_0x280bc2(0xa3)](_0x383977,_0x1c005a['length']),new DataConstraintError([_0x1c005a]);}else return _0x3dbd11;}};