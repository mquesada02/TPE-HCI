'use strict';function _0x223c(_0xa130e9,_0x453473){const _0x45555d=_0x4555();return _0x223c=function(_0x223cdd,_0x30e76a){_0x223cdd=_0x223cdd-0x1cd;let _0x3c7a89=_0x45555d[_0x223cdd];return _0x3c7a89;},_0x223c(_0xa130e9,_0x453473);}const _0x1788a9=_0x223c;(function(_0x44b7fd,_0x49a43e){const _0x52d8f5=_0x223c,_0x197c50=_0x44b7fd();while(!![]){try{const _0x110167=parseInt(_0x52d8f5(0x206))/0x1*(parseInt(_0x52d8f5(0x1e9))/0x2)+parseInt(_0x52d8f5(0x1ed))/0x3+-parseInt(_0x52d8f5(0x1f9))/0x4*(-parseInt(_0x52d8f5(0x20e))/0x5)+parseInt(_0x52d8f5(0x207))/0x6*(parseInt(_0x52d8f5(0x1e8))/0x7)+-parseInt(_0x52d8f5(0x203))/0x8*(parseInt(_0x52d8f5(0x1eb))/0x9)+-parseInt(_0x52d8f5(0x1ce))/0xa*(-parseInt(_0x52d8f5(0x1e7))/0xb)+-parseInt(_0x52d8f5(0x208))/0xc;if(_0x110167===_0x49a43e)break;else _0x197c50['push'](_0x197c50['shift']());}catch(_0x597702){_0x197c50['push'](_0x197c50['shift']());}}}(_0x4555,0x8838c));const bcrypt=require('bcrypt'),jwt=require(_0x1788a9(0x20a)),crypto=require(_0x1788a9(0x1db)),{RepositoryName,RepositoryFactory}=require('../repository/RepositoryFactory'),{ModelMapper}=require('./ModelMapper'),{registerVerification}=require(_0x1788a9(0x200)),{NotFoundError,InvalidCredentialsError,EmailVerificationError}=require('../errors/errors'),SALT_ROUNDS=0xa;async function sendVerificationEmail(_0x3b1306,_0x54c62d){const _0x92ce89=_0x1788a9,_0x4bb56a=RepositoryFactory['create'](RepositoryName['VerificationCode']);let _0xa8c0ee=await _0x4bb56a['queryByFilter']({'userId':_0x54c62d['id']});const _0x5c0ed8=crypto[_0x92ce89(0x1e4)](0x3)[_0x92ce89(0x1f8)](_0x92ce89(0x1fe))['toUpperCase'](),_0x1234e9=registerVerification&&registerVerification[_0x92ce89(0x1f6)]?registerVerification[_0x92ce89(0x1f6)]:0x18*0x3c*0x3c*0x3e8,_0x4d1e6d=new Date(new Date()[_0x92ce89(0x211)]()+_0x1234e9);!_0xa8c0ee?(_0xa8c0ee={'userId':_0x54c62d['id'],'code':_0x5c0ed8,'expirationDate':_0x4d1e6d},await _0x4bb56a[_0x92ce89(0x1d7)](_0xa8c0ee)):(_0xa8c0ee[_0x92ce89(0x1f1)]=_0x5c0ed8,_0xa8c0ee[_0x92ce89(0x1fb)]=_0x4d1e6d,await _0x4bb56a[_0x92ce89(0x1e5)](_0xa8c0ee));let _0x14d933=registerVerification['htmlBodyTemplate'][_0x92ce89(0x209)](_0x92ce89(0x1d2),_0x54c62d['id'])[_0x92ce89(0x209)]('<%CODE%>',_0x5c0ed8)[_0x92ce89(0x209)](_0x92ce89(0x202),_0x54c62d[_0x92ce89(0x1cd)])[_0x92ce89(0x209)]('<%EMAIL%>',_0x54c62d[_0x92ce89(0x1d5)])[_0x92ce89(0x209)](_0x92ce89(0x1d9),_0x4d1e6d)[_0x92ce89(0x209)](_0x92ce89(0x1f4),registerVerification[_0x92ce89(0x1fc)][_0x92ce89(0x209)]('<%USER_ID%>',_0x54c62d['id'])[_0x92ce89(0x209)](_0x92ce89(0x204),_0x5c0ed8)[_0x92ce89(0x209)](_0x92ce89(0x202),_0x54c62d['username'])[_0x92ce89(0x209)](_0x92ce89(0x1d8),_0x54c62d[_0x92ce89(0x1d5)]));const _0xfd3eab={'from':registerVerification[_0x92ce89(0x1d6)],'to':_0x54c62d['email'],'subject':registerVerification[_0x92ce89(0x1f3)],'html':_0x14d933};try{return await _0x3b1306['sendMail'](_0xfd3eab),!![];}catch(_0x2ccb3a){return console[_0x92ce89(0x1f2)](_0x2ccb3a),![];}}async function ensureUser(_0x18d063){const _0x52fd91=_0x1788a9,_0x5aade1=RepositoryFactory[_0x52fd91(0x1fa)](RepositoryName[_0x52fd91(0x201)]);let _0x13da01=await _0x5aade1[_0x52fd91(0x20b)](_0x18d063);if(!_0x13da01)throw new NotFoundError([_0x52fd91(0x1da)+_0x18d063+'\x20not\x20found']);return _0x13da01;}module[_0x1788a9(0x1ee)][_0x1788a9(0x1dd)]=async(_0x30ab39,_0x3a81e6)=>{const _0x1aaef4=_0x1788a9,_0xbfad3d=_0x3a81e6[_0x1aaef4(0x1ef)]?await bcrypt[_0x1aaef4(0x1df)](_0x3a81e6[_0x1aaef4(0x1ef)],SALT_ROUNDS):null,_0x49238f=ModelMapper[_0x1aaef4(0x1f0)](_0x3a81e6,_0xbfad3d),_0x1b5e72=RepositoryFactory[_0x1aaef4(0x1fa)](RepositoryName[_0x1aaef4(0x201)]),_0x315445=await _0x1b5e72[_0x1aaef4(0x1d7)](_0x49238f);return await sendVerificationEmail(_0x30ab39,_0x315445),ModelMapper[_0x1aaef4(0x20d)](_0x315445);},module[_0x1788a9(0x1ee)][_0x1788a9(0x1e6)]=async(_0xec9605,_0x2eeeac)=>{const _0x1c17ed=_0x1788a9;let _0x389c57=await ensureUser(_0xec9605['id']);ModelMapper['updateUserDto'](_0x389c57,_0x2eeeac);const _0x506862=RepositoryFactory[_0x1c17ed(0x1fa)](RepositoryName[_0x1c17ed(0x201)]);return _0x389c57=await _0x506862['update'](_0x389c57),ModelMapper[_0x1c17ed(0x20d)](_0x389c57);},module[_0x1788a9(0x1ee)][_0x1788a9(0x212)]=async _0x94659b=>{const _0x533f6a=_0x1788a9,_0x3d598d=RepositoryFactory['create'](RepositoryName[_0x533f6a(0x201)]),_0x2e3e7c=await _0x3d598d[_0x533f6a(0x1cf)](_0x94659b['id']);if(_0x2e3e7c===0x0)throw new NotFoundError([_0x533f6a(0x1da)+_0x94659b['id']+'\x20not\x20found']);},module['exports']['retrieve']=async _0x148619=>{let _0x24ed96=await ensureUser(_0x148619);return ModelMapper['mapPublicUserEntity'](_0x24ed96);},module[_0x1788a9(0x1ee)][_0x1788a9(0x1e2)]=async _0x30d5cb=>{const _0x2fc429=_0x1788a9;let _0x30e61d=await ensureUser(_0x30d5cb['id']);return ModelMapper[_0x2fc429(0x20d)](_0x30e61d);},module[_0x1788a9(0x1ee)]['retrieveList']=async(_0x379a6e,_0x5d447c,_0x19f910,_0x457aad,_0x2a7fa3)=>{const _0x444178=_0x1788a9,_0x1f38d6=_0x379a6e?{'column':_0x444178(0x1cd),'value':_0x379a6e}:null,_0x155e18=RepositoryFactory[_0x444178(0x1fa)](RepositoryName[_0x444178(0x201)]);let _0x3b51f4=await _0x155e18[_0x444178(0x20c)](null,_0x1f38d6,_0x5d447c,_0x19f910,_0x457aad,_0x2a7fa3);return _0x3b51f4['content']=_0x3b51f4[_0x444178(0x1de)]['map'](_0x321af2=>ModelMapper['mapPublicUserEntity'](_0x321af2)),_0x3b51f4;},module['exports'][_0x1788a9(0x1ea)]=async _0x209c61=>{const _0x3c6994=_0x1788a9,{email:_0x455c9c,code:_0x55c4ec}=_0x209c61,_0x37efea=RepositoryFactory[_0x3c6994(0x1fa)](RepositoryName[_0x3c6994(0x201)]),_0x38c292=await _0x37efea['queryByFilter']({'email':_0x455c9c});if(!_0x38c292)throw new NotFoundError([_0x3c6994(0x1ec)+_0x455c9c+_0x3c6994(0x1d3)]);if(_0x38c292['verified'])return;const _0x29f9c0=RepositoryFactory['create'](RepositoryName[_0x3c6994(0x1d4)]),_0x187824=await _0x29f9c0[_0x3c6994(0x20f)]({'userId':_0x38c292['id']});if(new Date(_0x187824[_0x3c6994(0x1fb)])<=new Date())throw new EmailVerificationError([_0x3c6994(0x1e3)]);if(_0x55c4ec['toUpperCase']()!==_0x187824['code'][_0x3c6994(0x1d0)]())throw new EmailVerificationError([_0x3c6994(0x1ff)]);_0x38c292[_0x3c6994(0x210)]=!![],await _0x37efea[_0x3c6994(0x1e5)](_0x38c292),await _0x29f9c0[_0x3c6994(0x1dc)]({'userId':_0x38c292['id']});},module[_0x1788a9(0x1ee)][_0x1788a9(0x1fd)]=async(_0x1b22cf,_0x54c9fb)=>{const _0x32c729=_0x1788a9,{email:_0x53649d}=_0x54c9fb,_0x125837=RepositoryFactory['create'](RepositoryName['User']),_0x3aa663=await _0x125837[_0x32c729(0x20f)]({'email':_0x53649d});if(!_0x3aa663)throw new NotFoundError([_0x32c729(0x1ec)+_0x53649d+_0x32c729(0x1d3)]);const _0x320777=RepositoryFactory[_0x32c729(0x1fa)](RepositoryName['VerificationCode']);await _0x320777[_0x32c729(0x1dc)]({'userId':_0x3aa663['id']}),await sendVerificationEmail(_0x1b22cf,_0x3aa663);},module[_0x1788a9(0x1ee)]['login']=async _0x8fd211=>{const _0x3c79a1=_0x1788a9,{username:_0x333e40,password:_0x2399e8}=_0x8fd211,_0x13ca9e=RepositoryFactory[_0x3c79a1(0x1fa)](RepositoryName[_0x3c79a1(0x201)]),_0x514134=await _0x13ca9e[_0x3c79a1(0x20f)]({'username':_0x333e40});if(!_0x514134)throw new InvalidCredentialsError([_0x3c79a1(0x1d1)]);const _0x23fcd6=await bcrypt[_0x3c79a1(0x1e1)](_0x2399e8,_0x514134[_0x3c79a1(0x1ef)]);if(!_0x23fcd6)throw new InvalidCredentialsError(['Password\x20does\x20not\x20match']);if(!_0x514134[_0x3c79a1(0x210)])throw new EmailVerificationError(['Email\x20not\x20verified']);const _0x2eaab7=Date['now'](),_0x4b3329=jwt[_0x3c79a1(0x205)]({'sub':_0x514134['id'],'iat':_0x2eaab7},process[_0x3c79a1(0x1e0)]['JWT_SECRET'],{'expiresIn':'30d'}),_0x2b7f76=RepositoryFactory[_0x3c79a1(0x1fa)](RepositoryName['Session']),_0x26ca69={'userId':_0x514134['id'],'iat':_0x2eaab7};return await _0x2b7f76[_0x3c79a1(0x1d7)](_0x26ca69),{'token':_0x4b3329};},module[_0x1788a9(0x1ee)][_0x1788a9(0x1f7)]=async _0x22f201=>{const _0xb266a9=_0x1788a9,_0x368475=RepositoryFactory['create'](RepositoryName[_0xb266a9(0x1f5)]);await _0x368475[_0xb266a9(0x1dc)]({'userId':_0x22f201['id'],'iat':_0x22f201['log_iat']});};function _0x4555(){const _0x610aac=['expirationDate','confirmationLink','resendVerificationEmail','hex','Invalid\x20verification\x20code','../config/mailing','User','<%USERNAME%>','16408gWMoXS','<%CODE%>','sign','1046323zUbsjk','6GdsreO','10128744rhOsmM','replace','jsonwebtoken','queryById','queryByAdvancedFilter','mapUserEntity','207335IBbgaY','queryByFilter','verified','getTime','remove','username','1190VtFDhv','deleteById','toUpperCase','Username\x20does\x20not\x20exist','<%USER_ID%>','\x20not\x20found','VerificationCode','email','from','insert','<%EMAIL%>','<%EXPIRATION_DATE%>','User\x20id\x20','crypto','deleteByFilter','add','content','hash','env','compare','retrieveCurrent','Expired\x20verification\x20code','randomBytes','update','modify','22583aRkBxz','2399593VseGmg','2cTqQiM','verifyEmail','4257EtTOgx','User\x20email\x20','1469754ldqQFj','exports','password','mapUserDto','code','log','subject','<%CONFIRM_LINK%>','Session','codeExpirationInterval','logout','toString','24fSZllB','create'];_0x4555=function(){return _0x610aac;};return _0x4555();}