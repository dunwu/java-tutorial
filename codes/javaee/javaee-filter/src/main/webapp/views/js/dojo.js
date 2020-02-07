/*
 Copyright (c) 2004-2006, The Dojo Foundation
 All Rights Reserved.

 Licensed under the Academic Free License version 2.1 or above OR the
 modified BSD license. For more information on Dojo licensing, see:

 http://dojotoolkit.org/community/licensing.shtml
 */

/*
 This is a compiled version of Dojo, built for deployment and not for
 development. To get an editable version, please visit:

 http://dojotoolkit.org

 for documentation and information on getting the source.
 */

if (typeof dojo == 'undefined') {
	var dj_global = this;
	var dj_currentContext = this;

	function dj_undef(_1, _2) {
		return (typeof (_2 || dj_currentContext)[_1] == 'undefined')
	}

	if (dj_undef('djConfig', this)) {
		var djConfig = {}
	}
	if (dj_undef('dojo', this)) {
		var dojo = {}
	}
	dojo.global = function () {
		return dj_currentContext
	};
	dojo.locale = djConfig.locale;
	dojo.version = {
		major: 0,
		minor: 4,
		patch: 3,
		flag: '-20070622',
		revision: Number('$Rev: 8617 $'.match(/[0-9]+/)[0]),
		toString: function () {
			with (dojo.version) {
				return major + '.' + minor + '.' + patch + flag + ' (' + revision + ')'
			}
		}
	};
	dojo.evalProp = function (_3, _4, _5) {
		if ((!_4) || (!_3)) {
			return undefined
		}
		if (!dj_undef(_3, _4)) {
			return _4[_3]
		}
		return (_5 ? (_4[_3] = {}) : undefined)
	};
	dojo.parseObjPath = function (_6, _7, _8) {
		var _9 = (_7 || dojo.global());
		var _a = _6.split('.');
		var _b = _a.pop();
		for (var i = 0, l = _a.length; i < l && _9; i++) {
			_9 = dojo.evalProp(_a[i], _9, _8)
		}
		return {obj: _9, prop: _b}
	};
	dojo.evalObjPath = function (_e, _f) {
		if (typeof _e != 'string') {
			return dojo.global()
		}
		if (_e.indexOf('.') == -1) {
			return dojo.evalProp(_e, dojo.global(), _f)
		}
		var ref = dojo.parseObjPath(_e, dojo.global(), _f);
		if (ref) {
			return dojo.evalProp(ref.prop, ref.obj, _f)
		}
		return null
	};
	dojo.errorToString = function (_11) {
		if (!dj_undef('message', _11)) {
			return _11.message
		} else {
			if (!dj_undef('description', _11)) {
				return _11.description
			} else {
				return _11
			}
		}
	};
	dojo.raise = function (_12, _13) {
		if (_13) {
			_12 = _12 + ': ' + dojo.errorToString(_13)
		} else {
			_12 = dojo.errorToString(_12)
		}
		try {
			if (djConfig.isDebug) {
				dojo.hostenv.println('FATAL exception raised: ' + _12)
			}
		} catch (e) {
		}
		throw _13 || Error(_12)
	};
	dojo.debug = function () {
	};
	dojo.debugShallow = function (obj) {
	};
	dojo.profile = {
		start: function () {
		}, end: function () {
		}, stop: function () {
		}, dump: function () {
		}
	};

	function dj_eval(_15) {
		return dj_global.eval ? dj_global.eval(_15) : eval(_15)
	}

	dojo.unimplemented = function (_16, _17) {
		var _18 = '\'' + _16 + '\' not implemented';
		if (_17 != null) {
			_18 += ' ' + _17
		}
		dojo.raise(_18)
	};
	dojo.deprecated = function (_19, _1a, _1b) {
		var _1c = 'DEPRECATED: ' + _19;
		if (_1a) {
			_1c += ' ' + _1a
		}
		if (_1b) {
			_1c += ' -- will be removed in version: ' + _1b
		}
		dojo.debug(_1c)
	};
	dojo.render = (function () {
		function vscaffold(_1d, _1e) {
			var tmp = {capable: false, support: {builtin: false, plugin: false}, prefixes: _1d};
			for (var i = 0; i < _1e.length; i++) {
				tmp[_1e[i]] = false
			}
			return tmp
		}

		return {
			name: '',
			ver: dojo.version,
			os: {win: false, linux: false, osx: false},
			html: vscaffold(['html'], ['ie', 'opera', 'khtml', 'safari', 'moz']),
			svg: vscaffold(['svg'], ['corel', 'adobe', 'batik']),
			vml: vscaffold(['vml'], ['ie']),
			swf: vscaffold(['Swf', 'Flash', 'Mm'], ['mm']),
			swt: vscaffold(['Swt'], ['ibm'])
		}
	})();
	dojo.hostenv = (function () {
		var _21 = {
			isDebug: false,
			allowQueryConfig: false,
			baseScriptUri: '',
			baseRelativePath: '',
			libraryScriptUri: '',
			iePreventClobber: false,
			ieClobberMinimal: true,
			preventBackButtonFix: true,
			delayMozLoadingFix: false,
			searchIds: [],
			parseWidgets: true
		};
		if (typeof djConfig == 'undefined') {
			djConfig = _21
		} else {
			for (var _22 in _21) {
				if (typeof djConfig[_22] == 'undefined') {
					djConfig[_22] = _21[_22]
				}
			}
		}
		return {
			name_: '(unset)', version_: '(unset)', getName: function () {
				return this.name_
			}, getVersion: function () {
				return this.version_
			}, getText: function (uri) {
				dojo.unimplemented('getText', 'uri=' + uri)
			}
		}
	})();
	dojo.hostenv.getBaseScriptUri = function () {
		if (djConfig.baseScriptUri.length) {
			return djConfig.baseScriptUri
		}
		var uri = String(djConfig.libraryScriptUri || djConfig.baseRelativePath);
		if (!uri) {
			dojo.raise('Nothing returned by getLibraryScriptUri(): ' + uri)
		}
		var _25 = uri.lastIndexOf('/');
		djConfig.baseScriptUri = djConfig.baseRelativePath;
		return djConfig.baseScriptUri
	};
	(function () {
		var _26 = {
			pkgFileName: '__package__',
			loading_modules_: {},
			loaded_modules_: {},
			addedToLoadingCount: [],
			removedFromLoadingCount: [],
			inFlightCount: 0,
			modulePrefixes_: {dojo: {name: 'dojo', value: 'src'}},
			setModulePrefix: function (_27, _28) {
				this.modulePrefixes_[_27] = {name: _27, value: _28}
			},
			moduleHasPrefix: function (_29) {
				var mp = this.modulePrefixes_;
				return Boolean(mp[_29] && mp[_29].value)
			},
			getModulePrefix: function (_2b) {
				if (this.moduleHasPrefix(_2b)) {
					return this.modulePrefixes_[_2b].value
				}
				return _2b
			},
			getTextStack: [],
			loadUriStack: [],
			loadedUris: [],
			post_load_: false,
			modulesLoadedListeners: [],
			unloadListeners: [],
			loadNotifying: false
		};
		for (var _2c in _26) {
			dojo.hostenv[_2c] = _26[_2c]
		}
	})();
	dojo.hostenv.loadPath = function (_2d, _2e, cb) {
		var uri;
		if (_2d.charAt(0) == '/' || _2d.match(/^\w+:/)) {
			uri = _2d
		} else {
			uri = this.getBaseScriptUri() + _2d
		}
		if (djConfig.cacheBust && dojo.render.html.capable) {
			uri += '?' + String(djConfig.cacheBust).replace(/\W+/g, '')
		}
		try {
			return !_2e ? this.loadUri(uri, cb) : this.loadUriAndCheck(uri, _2e, cb)
		} catch (e) {
			dojo.debug(e);
			return false
		}
	};
	dojo.hostenv.loadUri = function (uri, cb) {
		if (this.loadedUris[uri]) {
			return true
		}
		var _33 = this.getText(uri, null, true);
		if (!_33) {
			return false
		}
		this.loadedUris[uri] = true;
		if (cb) {
			_33 = '(' + _33 + ')'
		}
		var _34 = dj_eval(_33);
		if (cb) {
			cb(_34)
		}
		return true
	};
	dojo.hostenv.loadUriAndCheck = function (uri, _36, cb) {
		var ok = true;
		try {
			ok = this.loadUri(uri, cb)
		} catch (e) {
			dojo.debug('failed loading ', uri, ' with error: ', e)
		}
		return Boolean(ok && this.findModule(_36, false))
	};
	dojo.loaded = function () {
	};
	dojo.unloaded = function () {
	};
	dojo.hostenv.loaded = function () {
		this.loadNotifying = true;
		this.post_load_ = true;
		var mll = this.modulesLoadedListeners;
		for (var x = 0; x < mll.length; x++) {
			mll[x]()
		}
		this.modulesLoadedListeners = [];
		this.loadNotifying = false;
		dojo.loaded()
	};
	dojo.hostenv.unloaded = function () {
		var mll = this.unloadListeners;
		while (mll.length) {
			(mll.pop())()
		}
		dojo.unloaded()
	};
	dojo.addOnLoad = function (obj, _3d) {
		var dh = dojo.hostenv;
		if (arguments.length == 1) {
			dh.modulesLoadedListeners.push(obj)
		} else {
			if (arguments.length > 1) {
				dh.modulesLoadedListeners.push(function () {
					obj[_3d]()
				})
			}
		}
		if (dh.post_load_ && dh.inFlightCount == 0 && !dh.loadNotifying) {
			dh.callLoaded()
		}
	};
	dojo.addOnUnload = function (obj, _40) {
		var dh = dojo.hostenv;
		if (arguments.length == 1) {
			dh.unloadListeners.push(obj)
		} else {
			if (arguments.length > 1) {
				dh.unloadListeners.push(function () {
					obj[_40]()
				})
			}
		}
	};
	dojo.hostenv.modulesLoaded = function () {
		if (this.post_load_) {
			return
		}
		if (this.loadUriStack.length == 0 && this.getTextStack.length == 0) {
			if (this.inFlightCount > 0) {
				dojo.debug('files still in flight!');
				return
			}
			dojo.hostenv.callLoaded()
		}
	};
	dojo.hostenv.callLoaded = function () {
		if (typeof setTimeout == 'object' || (djConfig['useXDomain'] && dojo.render.html.opera)) {
			setTimeout('dojo.hostenv.loaded();', 0)
		} else {
			dojo.hostenv.loaded()
		}
	};
	dojo.hostenv.getModuleSymbols = function (_42) {
		var _43 = _42.split('.');
		for (var i = _43.length; i > 0; i--) {
			var _45 = _43.slice(0, i).join('.');
			if ((i == 1) && !this.moduleHasPrefix(_45)) {
				_43[0] = '../' + _43[0]
			} else {
				var _46 = this.getModulePrefix(_45);
				if (_46 != _45) {
					_43.splice(0, i, _46);
					break
				}
			}
		}
		return _43
	};
	dojo.hostenv._global_omit_module_check = false;
	dojo.hostenv.loadModule = function (_47, _48, _49) {
		if (!_47) {
			return
		}
		_49 = this._global_omit_module_check || _49;
		var _4a = this.findModule(_47, false);
		if (_4a) {
			return _4a
		}
		if (dj_undef(_47, this.loading_modules_)) {
			this.addedToLoadingCount.push(_47)
		}
		this.loading_modules_[_47] = 1;
		var _4b = _47.replace(/\./g, '/') + '.js';
		var _4c = _47.split('.');
		var _4d = this.getModuleSymbols(_47);
		var _4e = ((_4d[0].charAt(0) != '/') && !_4d[0].match(/^\w+:/));
		var _4f = _4d[_4d.length - 1];
		var ok;
		if (_4f == '*') {
			_47 = _4c.slice(0, -1).join('.');
			while (_4d.length) {
				_4d.pop();
				_4d.push(this.pkgFileName);
				_4b = _4d.join('/') + '.js';
				if (_4e && _4b.charAt(0) == '/') {
					_4b = _4b.slice(1)
				}
				ok = this.loadPath(_4b, !_49 ? _47 : null);
				if (ok) {
					break
				}
				_4d.pop()
			}
		} else {
			_4b = _4d.join('/') + '.js';
			_47 = _4c.join('.');
			var _51 = !_49 ? _47 : null;
			ok = this.loadPath(_4b, _51);
			if (!ok && !_48) {
				_4d.pop();
				while (_4d.length) {
					_4b = _4d.join('/') + '.js';
					ok = this.loadPath(_4b, _51);
					if (ok) {
						break
					}
					_4d.pop();
					_4b = _4d.join('/') + '/' + this.pkgFileName + '.js';
					if (_4e && _4b.charAt(0) == '/') {
						_4b = _4b.slice(1)
					}
					ok = this.loadPath(_4b, _51);
					if (ok) {
						break
					}
				}
			}
			if (!ok && !_49) {
				dojo.raise('Could not load \'' + _47 + '\'; last tried \'' + _4b + '\'')
			}
		}
		if (!_49 && !this['isXDomain']) {
			_4a = this.findModule(_47, false);
			if (!_4a) {
				dojo.raise('symbol \'' + _47 + '\' is not defined after loading \'' + _4b + '\'')
			}
		}
		return _4a
	};
	dojo.hostenv.startPackage = function (_52) {
		var _53 = String(_52);
		var _54 = _53;
		var _55 = _52.split(/\./);
		if (_55[_55.length - 1] == '*') {
			_55.pop();
			_54 = _55.join('.')
		}
		var _56 = dojo.evalObjPath(_54, true);
		this.loaded_modules_[_53] = _56;
		this.loaded_modules_[_54] = _56;
		return _56
	};
	dojo.hostenv.findModule = function (_57, _58) {
		var lmn = String(_57);
		if (this.loaded_modules_[lmn]) {
			return this.loaded_modules_[lmn]
		}
		if (_58) {
			dojo.raise('no loaded module named \'' + _57 + '\'')
		}
		return null
	};
	dojo.kwCompoundRequire = function (_5a) {
		var _5b = _5a['common'] || [];
		var _5c = _5a[dojo.hostenv.name_] ? _5b.concat(_5a[dojo.hostenv.name_] || []) : _5b.concat(_5a['default'] || []);
		for (var x = 0; x < _5c.length; x++) {
			var _5e = _5c[x];
			if (_5e.constructor == Array) {
				dojo.hostenv.loadModule.apply(dojo.hostenv, _5e)
			} else {
				dojo.hostenv.loadModule(_5e)
			}
		}
	};
	dojo.require = function (_5f) {
		dojo.hostenv.loadModule.apply(dojo.hostenv, arguments)
	};
	dojo.requireIf = function (_60, _61) {
		var _62 = arguments[0];
		if ((_62 === true) || (_62 == 'common') || (_62 && dojo.render[_62].capable)) {
			var _63 = [];
			for (var i = 1; i < arguments.length; i++) {
				_63.push(arguments[i])
			}
			dojo.require.apply(dojo, _63)
		}
	};
	dojo.requireAfterIf = dojo.requireIf;
	dojo.provide = function (_65) {
		return dojo.hostenv.startPackage.apply(dojo.hostenv, arguments)
	};
	dojo.registerModulePath = function (_66, _67) {
		return dojo.hostenv.setModulePrefix(_66, _67)
	};
	if (djConfig['modulePaths']) {
		for (var param in djConfig['modulePaths']) {
			dojo.registerModulePath(param, djConfig['modulePaths'][param])
		}
	}
	dojo.setModulePrefix = function (_68, _69) {
		dojo.deprecated('dojo.setModulePrefix("' + _68 + '", "' + _69 + '")', 'replaced by dojo.registerModulePath', '0.5');
		return dojo.registerModulePath(_68, _69)
	};
	dojo.exists = function (obj, _6b) {
		var p = _6b.split('.');
		for (var i = 0; i < p.length; i++) {
			if (!obj[p[i]]) {
				return false
			}
			obj = obj[p[i]]
		}
		return true
	};
	dojo.hostenv.normalizeLocale = function (_6e) {
		var _6f = _6e ? _6e.toLowerCase() : dojo.locale;
		if (_6f == 'root') {
			_6f = 'ROOT'
		}
		return _6f
	};
	dojo.hostenv.searchLocalePath = function (_70, _71, _72) {
		_70 = dojo.hostenv.normalizeLocale(_70);
		var _73 = _70.split('-');
		var _74 = [];
		for (var i = _73.length; i > 0; i--) {
			_74.push(_73.slice(0, i).join('-'))
		}
		_74.push(false);
		if (_71) {
			_74.reverse()
		}
		for (var j = _74.length - 1; j >= 0; j--) {
			var loc = _74[j] || 'ROOT';
			var _78 = _72(loc);
			if (_78) {
				break
			}
		}
	};
	dojo.hostenv.localesGenerated;
	dojo.hostenv.registerNlsPrefix = function () {
		dojo.registerModulePath('nls', 'nls')
	};
	dojo.hostenv.preloadLocalizations = function () {
		if (dojo.hostenv.localesGenerated) {
			dojo.hostenv.registerNlsPrefix();

			function preload(_79) {
				_79 = dojo.hostenv.normalizeLocale(_79);
				dojo.hostenv.searchLocalePath(_79, true, function (loc) {
					for (var i = 0; i < dojo.hostenv.localesGenerated.length; i++) {
						if (dojo.hostenv.localesGenerated[i] == loc) {
							dojo['require']('nls.dojo_' + loc);
							return true
						}
					}
					return false
				})
			}

			preload();
			var _7c = djConfig.extraLocale || [];
			for (var i = 0; i < _7c.length; i++) {
				preload(_7c[i])
			}
		}
		dojo.hostenv.preloadLocalizations = function () {
		}
	};
	dojo.requireLocalization = function (_7e, _7f, _80, _81) {
		dojo.hostenv.preloadLocalizations();
		var _82 = dojo.hostenv.normalizeLocale(_80);
		var _83 = [_7e, 'nls', _7f].join('.');
		var _84 = '';
		if (_81) {
			var _85 = _81.split(',');
			for (var i = 0; i < _85.length; i++) {
				if (_82.indexOf(_85[i]) == 0) {
					if (_85[i].length > _84.length) {
						_84 = _85[i]
					}
				}
			}
			if (!_84) {
				_84 = 'ROOT'
			}
		}
		var _87 = _81 ? _84 : _82;
		var _88 = dojo.hostenv.findModule(_83);
		var _89 = null;
		if (_88) {
			if (djConfig.localizationComplete && _88._built) {
				return
			}
			var _8a = _87.replace('-', '_');
			var _8b = _83 + '.' + _8a;
			_89 = dojo.hostenv.findModule(_8b)
		}
		if (!_89) {
			_88 = dojo.hostenv.startPackage(_83);
			var _8c = dojo.hostenv.getModuleSymbols(_7e);
			var _8d = _8c.concat('nls').join('/');
			var _8e;
			dojo.hostenv.searchLocalePath(_87, _81, function (loc) {
				var _90 = loc.replace('-', '_');
				var _91 = _83 + '.' + _90;
				var _92 = false;
				if (!dojo.hostenv.findModule(_91)) {
					dojo.hostenv.startPackage(_91);
					var _93 = [_8d];
					if (loc != 'ROOT') {
						_93.push(loc)
					}
					_93.push(_7f);
					var _94 = _93.join('/') + '.js';
					_92 = dojo.hostenv.loadPath(_94, null, function (_95) {
						var _96 = function () {
						};
						_96.prototype = _8e;
						_88[_90] = new _96();
						for (var j in _95) {
							_88[_90][j] = _95[j]
						}
					})
				} else {
					_92 = true
				}
				if (_92 && _88[_90]) {
					_8e = _88[_90]
				} else {
					_88[_90] = _8e
				}
				if (_81) {
					return true
				}
			})
		}
		if (_81 && _82 != _84) {
			_88[_82.replace('-', '_')] = _88[_84.replace('-', '_')]
		}
	};
	(function () {
		var _98 = djConfig.extraLocale;
		if (_98) {
			if (!_98 instanceof Array) {
				_98 = [_98]
			}
			var req = dojo.requireLocalization;
			dojo.requireLocalization = function (m, b, _9c, _9d) {
				req(m, b, _9c, _9d);
				if (_9c) {
					return
				}
				for (var i = 0; i < _98.length; i++) {
					req(m, b, _98[i], _9d)
				}
			}
		}
	})()
}
if (typeof window != 'undefined') {
	(function () {
		if (djConfig.allowQueryConfig) {
			var _9f = document.location.toString();
			var _a0 = _9f.split('?', 2);
			if (_a0.length > 1) {
				var _a1 = _a0[1];
				var _a2 = _a1.split('&');
				for (var x in _a2) {
					var sp = _a2[x].split('=');
					if ((sp[0].length > 9) && (sp[0].substr(0, 9) == 'djConfig.')) {
						var opt = sp[0].substr(9);
						try {
							djConfig[opt] = eval(sp[1])
						} catch (e) {
							djConfig[opt] = sp[1]
						}
					}
				}
			}
		}
		if (((djConfig['baseScriptUri'] == '') || (djConfig['baseRelativePath'] == '')) && (document
			&& document.getElementsByTagName)) {
			var _a6 = document.getElementsByTagName('script');
			var _a7 = /(__package__|dojo|bootstrap1)\.js([\?\.]|$)/i;
			for (var i = 0; i < _a6.length; i++) {
				var src = _a6[i].getAttribute('src');
				if (!src) {
					continue
				}
				var m = src.match(_a7);
				if (m) {
					var _ab = src.substring(0, m.index);
					if (src.indexOf('bootstrap1') > -1) {
						_ab += '../'
					}
					if (!this['djConfig']) {
						djConfig = {}
					}
					if (djConfig['baseScriptUri'] == '') {
						djConfig['baseScriptUri'] = _ab
					}
					if (djConfig['baseRelativePath'] == '') {
						djConfig['baseRelativePath'] = _ab
					}
					break
				}
			}
		}
		var dr = dojo.render;
		var drh = dojo.render.html;
		var drs = dojo.render.svg;
		var dua = (drh.UA = navigator.userAgent);
		var dav = (drh.AV = navigator.appVersion);
		var t = true;
		var f = false;
		drh.capable = t;
		drh.support.builtin = t;
		dr.ver = parseFloat(drh.AV);
		dr.os.mac = dav.indexOf('Macintosh') >= 0;
		dr.os.win = dav.indexOf('Windows') >= 0;
		dr.os.linux = dav.indexOf('X11') >= 0;
		drh.opera = dua.indexOf('Opera') >= 0;
		drh.khtml = (dav.indexOf('Konqueror') >= 0) || (dav.indexOf('Safari') >= 0);
		drh.safari = dav.indexOf('Safari') >= 0;
		var _b3 = dua.indexOf('Gecko');
		drh.mozilla = drh.moz = (_b3 >= 0) && (!drh.khtml);
		if (drh.mozilla) {
			drh.geckoVersion = dua.substring(_b3 + 6, _b3 + 14)
		}
		drh.ie = (document.all) && (!drh.opera);
		drh.ie50 = drh.ie && dav.indexOf('MSIE 5.0') >= 0;
		drh.ie55 = drh.ie && dav.indexOf('MSIE 5.5') >= 0;
		drh.ie60 = drh.ie && dav.indexOf('MSIE 6.0') >= 0;
		drh.ie70 = drh.ie && dav.indexOf('MSIE 7.0') >= 0;
		var cm = document['compatMode'];
		drh.quirks = (cm == 'BackCompat') || (cm == 'QuirksMode') || drh.ie55 || drh.ie50;
		dojo.locale = dojo.locale || (drh.ie ? navigator.userLanguage : navigator.language).toLowerCase();
		dr.vml.capable = drh.ie;
		drs.capable = f;
		drs.support.plugin = f;
		drs.support.builtin = f;
		var _b5 = window['document'];
		var tdi = _b5['implementation'];
		if (drh.ie && (window.location.protocol == 'file:')) {
			djConfig.ieForceActiveXXhr = true
		}
		if ((tdi) && (tdi['hasFeature']) && (tdi.hasFeature('org.w3c.dom.svg', '1.0'))) {
			drs.capable = t;
			drs.support.builtin = t;
			drs.support.plugin = f
		}
		if (drh.safari) {
			var tmp = dua.split('AppleWebKit/')[1];
			var ver = parseFloat(tmp.split(' ')[0]);
			if (ver >= 420) {
				drs.capable = t;
				drs.support.builtin = t;
				drs.support.plugin = f
			}
		} else {
		}
	})();
	dojo.hostenv.startPackage('dojo.hostenv');
	dojo.render.name = dojo.hostenv.name_ = 'browser';
	dojo.hostenv.searchIds = [];
	dojo.hostenv._XMLHTTP_PROGIDS = ['Msxml2.XMLHTTP', 'Microsoft.XMLHTTP', 'Msxml2.XMLHTTP.4.0'];
	dojo.hostenv.getXmlhttpObject = function () {
		var _b9 = null;
		var _ba = null;
		if (!dojo.render.html.ie || !djConfig.ieForceActiveXXhr) {
			try {
				_b9 = new XMLHttpRequest()
			} catch (e) {
			}
		}
		if (!_b9) {
			for (var i = 0; i < 3; ++i) {
				var _bc = dojo.hostenv._XMLHTTP_PROGIDS[i];
				try {
					_b9 = new ActiveXObject(_bc)
				} catch (e) {
					_ba = e
				}
				if (_b9) {
					dojo.hostenv._XMLHTTP_PROGIDS = [_bc];
					break
				}
			}
		}
		if (!_b9) {
			return dojo.raise('XMLHTTP not available', _ba)
		}
		return _b9
	};
	dojo.hostenv._blockAsync = false;
	dojo.hostenv.getText = function (uri, _be, _bf) {
		if (!_be) {
			this._blockAsync = true
		}
		var _c0 = this.getXmlhttpObject();

		function isDocumentOk(_c1) {
			var _c2 = _c1['status'];
			return Boolean((!_c2) || ((200 <= _c2) && (300 > _c2)) || (_c2 == 304))
		}

		if (_be) {
			var _c3 = this, _c4 = null, gbl = dojo.global();
			var xhr = dojo.evalObjPath('dojo.io.XMLHTTPTransport');
			_c0.onreadystatechange = function () {
				if (_c4) {
					gbl.clearTimeout(_c4);
					_c4 = null
				}
				if (_c3._blockAsync || (xhr && xhr._blockAsync)) {
					_c4 = gbl.setTimeout(function () {
						_c0.onreadystatechange.apply(this)
					}, 10)
				} else {
					if (4 == _c0.readyState) {
						if (isDocumentOk(_c0)) {
							_be(_c0.responseText)
						}
					}
				}
			}
		}
		_c0.open('GET', uri, _be ? true : false);
		try {
			_c0.send(null);
			if (_be) {
				return null
			}
			if (!isDocumentOk(_c0)) {
				var err = Error('Unable to load ' + uri + ' status:' + _c0.status);
				err.status = _c0.status;
				err.responseText = _c0.responseText;
				throw err
			}
		} catch (e) {
			this._blockAsync = false;
			if ((_bf) && (!_be)) {
				return null
			} else {
				throw e
			}
		}
		this._blockAsync = false;
		return _c0.responseText
	};
	dojo.hostenv.defaultDebugContainerId = 'dojoDebug';
	dojo.hostenv._println_buffer = [];
	dojo.hostenv._println_safe = false;
	dojo.hostenv.println = function (_c8) {
		if (!dojo.hostenv._println_safe) {
			dojo.hostenv._println_buffer.push(_c8)
		} else {
			try {
				var _c9 = document.getElementById(djConfig.debugContainerId ? djConfig.debugContainerId : dojo.hostenv.defaultDebugContainerId);
				if (!_c9) {
					_c9 = dojo.body()
				}
				var div = document.createElement('div');
				div.appendChild(document.createTextNode(_c8));
				_c9.appendChild(div)
			} catch (e) {
				try {
					document.write('<div>' + _c8 + '</div>')
				} catch (e2) {
					window.status = _c8
				}
			}
		}
	};
	dojo.addOnLoad(function () {
		dojo.hostenv._println_safe = true;
		while (dojo.hostenv._println_buffer.length > 0) {
			dojo.hostenv.println(dojo.hostenv._println_buffer.shift())
		}
	});

	function dj_addNodeEvtHdlr(_cb, _cc, fp) {
		var _ce = _cb['on' + _cc] || function () {
		};
		_cb['on' + _cc] = function () {
			fp.apply(_cb, arguments);
			_ce.apply(_cb, arguments)
		};
		return true
	}

	dojo.hostenv._djInitFired = false;

	function dj_load_init(e) {
		dojo.hostenv._djInitFired = true;
		var _d0 = (e && e.type) ? e.type.toLowerCase() : 'load';
		if (arguments.callee.initialized || (_d0 != 'domcontentloaded' && _d0 != 'load')) {
			return
		}
		arguments.callee.initialized = true;
		if (typeof (_timer) != 'undefined') {
			clearInterval(_timer);
			delete _timer
		}
		var _d1 = function () {
			if (dojo.render.html.ie) {
				dojo.hostenv.makeWidgets()
			}
		};
		if (dojo.hostenv.inFlightCount == 0) {
			_d1();
			dojo.hostenv.modulesLoaded()
		} else {
			dojo.hostenv.modulesLoadedListeners.unshift(_d1)
		}
	}

	if (document.addEventListener) {
		if (dojo.render.html.opera || (dojo.render.html.moz && (djConfig['enableMozDomContentLoaded'] === true))) {
			document.addEventListener('DOMContentLoaded', dj_load_init, null)
		}
		window.addEventListener('load', dj_load_init, null)
	}
	if (dojo.render.html.ie && dojo.render.os.win) {
		document.attachEvent('onreadystatechange', function (e) {
			if (document.readyState == 'complete') {
				dj_load_init()
			}
		})
	}
	if (/(WebKit|khtml)/i.test(navigator.userAgent)) {
		var _timer = setInterval(function () {
			if (/loaded|complete/.test(document.readyState)) {
				dj_load_init()
			}
		}, 10)
	}
	if (dojo.render.html.ie) {
		dj_addNodeEvtHdlr(window, 'beforeunload', function () {
			dojo.hostenv._unloading = true;
			window.setTimeout(function () {
				dojo.hostenv._unloading = false
			}, 0)
		})
	}
	dj_addNodeEvtHdlr(window, 'unload', function () {
		dojo.hostenv.unloaded();
		if ((!dojo.render.html.ie) || (dojo.render.html.ie && dojo.hostenv._unloading)) {
			dojo.hostenv.unloaded()
		}
	});
	dojo.hostenv.makeWidgets = function () {
		var _d3 = [];
		if (djConfig.searchIds && djConfig.searchIds.length > 0) {
			_d3 = _d3.concat(djConfig.searchIds)
		}
		if (dojo.hostenv.searchIds && dojo.hostenv.searchIds.length > 0) {
			_d3 = _d3.concat(dojo.hostenv.searchIds)
		}
		if ((djConfig.parseWidgets) || (_d3.length > 0)) {
			if (dojo.evalObjPath('dojo.widget.Parse')) {
				var _d4 = new dojo.xml.Parse();
				if (_d3.length > 0) {
					for (var x = 0; x < _d3.length; x++) {
						var _d6 = document.getElementById(_d3[x]);
						if (!_d6) {
							continue
						}
						var _d7 = _d4.parseElement(_d6, null, true);
						dojo.widget.getParser().createComponents(_d7)
					}
				} else {
					if (djConfig.parseWidgets) {
						var _d7 = _d4.parseElement(dojo.body(), null, true);
						dojo.widget.getParser().createComponents(_d7)
					}
				}
			}
		}
	};
	dojo.addOnLoad(function () {
		if (!dojo.render.html.ie) {
			dojo.hostenv.makeWidgets()
		}
	});
	try {
		if (dojo.render.html.ie) {
			document.namespaces.add('v', 'urn:schemas-microsoft-com:vml');
			document.createStyleSheet().addRule('v\\:*', 'behavior:url(#default#VML)')
		}
	} catch (e) {
	}
	dojo.hostenv.writeIncludes = function () {
	};
	if (!dj_undef('document', this)) {
		dj_currentDocument = this.document
	}
	dojo.doc = function () {
		return dj_currentDocument
	};
	dojo.body = function () {
		return dojo.doc().body || dojo.doc().getElementsByTagName('body')[0]
	};
	dojo.byId = function (id, doc) {
		if ((id) && ((typeof id == 'string') || (id instanceof String))) {
			if (!doc) {
				doc = dj_currentDocument
			}
			var ele = doc.getElementById(id);
			if (ele && (ele.id != id) && doc.all) {
				ele = null;
				eles = doc.all[id];
				if (eles) {
					if (eles.length) {
						for (var i = 0; i < eles.length; i++) {
							if (eles[i].id == id) {
								ele = eles[i];
								break
							}
						}
					} else {
						ele = eles
					}
				}
			}
			return ele
		}
		return id
	};
	dojo.setContext = function (_dc, _dd) {
		dj_currentContext = _dc;
		dj_currentDocument = _dd
	};
	dojo._fireCallback = function (_de, _df, _e0) {
		if ((_df) && ((typeof _de == 'string') || (_de instanceof String))) {
			_de = _df[_de]
		}
		return (_df ? _de.apply(_df, _e0 || []) : _de())
	};
	dojo.withGlobal = function (_e1, _e2, _e3, _e4) {
		var _e5;
		var _e6 = dj_currentContext;
		var _e7 = dj_currentDocument;
		try {
			dojo.setContext(_e1, _e1.document);
			_e5 = dojo._fireCallback(_e2, _e3, _e4)
		} finally {
			dojo.setContext(_e6, _e7)
		}
		return _e5
	};
	dojo.withDoc = function (_e8, _e9, _ea, _eb) {
		var _ec;
		var _ed = dj_currentDocument;
		try {
			dj_currentDocument = _e8;
			_ec = dojo._fireCallback(_e9, _ea, _eb)
		} finally {
			dj_currentDocument = _ed
		}
		return _ec
	}
}
dojo.requireIf((djConfig['isDebug'] || djConfig['debugAtAllCosts']), 'dojo.debug');
dojo.requireIf(djConfig['debugAtAllCosts'] && !window.widget && !djConfig['useXDomain'], 'dojo.browser_debug');
dojo.requireIf(djConfig['debugAtAllCosts'] && !window.widget && djConfig['useXDomain'], 'dojo.browser_debug_xd');
dojo.provide('dojo.string.common');
dojo.string.trim = function (str, wh) {
	if (!str.replace) {
		return str
	}
	if (!str.length) {
		return str
	}
	var re = (wh > 0) ? (/^\s+/) : (wh < 0) ? (/\s+$/) : (/^\s+|\s+$/g);
	return str.replace(re, '')
};
dojo.string.trimStart = function (str) {
	return dojo.string.trim(str, 1)
};
dojo.string.trimEnd = function (str) {
	return dojo.string.trim(str, -1)
};
dojo.string.repeat = function (str, _f4, _f5) {
	var out = '';
	for (var i = 0; i < _f4; i++) {
		out += str;
		if (_f5 && i < _f4 - 1) {
			out += _f5
		}
	}
	return out
};
dojo.string.pad = function (str, len, c, dir) {
	var out = String(str);
	if (!c) {
		c = '0'
	}
	if (!dir) {
		dir = 1
	}
	while (out.length < len) {
		if (dir > 0) {
			out = c + out
		} else {
			out += c
		}
	}
	return out
};
dojo.string.padLeft = function (str, len, c) {
	return dojo.string.pad(str, len, c, 1)
};
dojo.string.padRight = function (str, len, c) {
	return dojo.string.pad(str, len, c, -1)
};
dojo.provide('dojo.string');
dojo.provide('dojo.lang.common');
dojo.lang.inherits = function (_103, _104) {
	if (!dojo.lang.isFunction(_104)) {
		dojo.raise('dojo.inherits: superclass argument [' + _104 + '] must be a function (subclass: [' + _103 + '\']')
	}
	_103.prototype = new _104();
	_103.prototype.constructor = _103;
	_103.superclass = _104.prototype;
	_103['super'] = _104.prototype
};
dojo.lang._mixin = function (obj, _106) {
	var tobj = {};
	for (var x in _106) {
		if ((typeof tobj[x] == 'undefined') || (tobj[x] != _106[x])) {
			obj[x] = _106[x]
		}
	}
	if (dojo.render.html.ie
		&& (typeof (_106['toString']) == 'function')
		&& (_106['toString'] != obj['toString'])
		&& (_106['toString'] != tobj['toString'])) {
		obj.toString = _106.toString
	}
	return obj
};
dojo.lang.mixin = function (obj, _10a) {
	for (var i = 1, l = arguments.length; i < l; i++) {
		dojo.lang._mixin(obj, arguments[i])
	}
	return obj
};
dojo.lang.extend = function (_10d, _10e) {
	for (var i = 1, l = arguments.length; i < l; i++) {
		dojo.lang._mixin(_10d.prototype, arguments[i])
	}
	return _10d
};
dojo.inherits = dojo.lang.inherits;
dojo.mixin = dojo.lang.mixin;
dojo.extend = dojo.lang.extend;
dojo.lang.find = function (_111, _112, _113, _114) {
	if (!dojo.lang.isArrayLike(_111) && dojo.lang.isArrayLike(_112)) {
		dojo.deprecated('dojo.lang.find(value, array)', 'use dojo.lang.find(array, value) instead', '0.5');
		var temp = _111;
		_111 = _112;
		_112 = temp
	}
	var _116 = dojo.lang.isString(_111);
	if (_116) {
		_111 = _111.split('')
	}
	if (_114) {
		var step = -1;
		var i = _111.length - 1;
		var end = -1
	} else {
		var step = 1;
		var i = 0;
		var end = _111.length
	}
	if (_113) {
		while (i != end) {
			if (_111[i] === _112) {
				return i
			}
			i += step
		}
	} else {
		while (i != end) {
			if (_111[i] == _112) {
				return i
			}
			i += step
		}
	}
	return -1
};
dojo.lang.indexOf = dojo.lang.find;
dojo.lang.findLast = function (_11a, _11b, _11c) {
	return dojo.lang.find(_11a, _11b, _11c, true)
};
dojo.lang.lastIndexOf = dojo.lang.findLast;
dojo.lang.inArray = function (_11d, _11e) {
	return dojo.lang.find(_11d, _11e) > -1
};
dojo.lang.isObject = function (it) {
	if (typeof it == 'undefined') {
		return false
	}
	return (typeof it == 'object' || it === null || dojo.lang.isArray(it) || dojo.lang.isFunction(it))
};
dojo.lang.isArray = function (it) {
	return (it && it instanceof Array || typeof it == 'array')
};
dojo.lang.isArrayLike = function (it) {
	if ((!it) || (dojo.lang.isUndefined(it))) {
		return false
	}
	if (dojo.lang.isString(it)) {
		return false
	}
	if (dojo.lang.isFunction(it)) {
		return false
	}
	if (dojo.lang.isArray(it)) {
		return true
	}
	if ((it.tagName) && (it.tagName.toLowerCase() == 'form')) {
		return false
	}
	if (dojo.lang.isNumber(it.length) && isFinite(it.length)) {
		return true
	}
	return false
};
dojo.lang.isFunction = function (it) {
	return (it instanceof Function || typeof it == 'function')
};
(function () {
	if ((dojo.render.html.capable) && (dojo.render.html['safari'])) {
		dojo.lang.isFunction = function (it) {
			if ((typeof (it) == 'function') && (it == '[object NodeList]')) {
				return false
			}
			return (it instanceof Function || typeof it == 'function')
		}
	}
})();
dojo.lang.isString = function (it) {
	return (typeof it == 'string' || it instanceof String)
};
dojo.lang.isAlien = function (it) {
	if (!it) {
		return false
	}
	return !dojo.lang.isFunction(it) && /\{\s*\[native code\]\s*\}/.test(String(it))
};
dojo.lang.isBoolean = function (it) {
	return (it instanceof Boolean || typeof it == 'boolean')
};
dojo.lang.isNumber = function (it) {
	return (it instanceof Number || typeof it == 'number')
};
dojo.lang.isUndefined = function (it) {
	return ((typeof (it) == 'undefined') && (it == undefined))
};
dojo.provide('dojo.lang.extras');
dojo.lang.setTimeout = function (func, _12a) {
	var _12b = window, _12c = 2;
	if (!dojo.lang.isFunction(func)) {
		_12b = func;
		func = _12a;
		_12a = arguments[2];
		_12c++
	}
	if (dojo.lang.isString(func)) {
		func = _12b[func]
	}
	var args = [];
	for (var i = _12c; i < arguments.length; i++) {
		args.push(arguments[i])
	}
	return dojo.global().setTimeout(function () {
		func.apply(_12b, args)
	}, _12a)
};
dojo.lang.clearTimeout = function (_12f) {
	dojo.global().clearTimeout(_12f)
};
dojo.lang.getNameInObj = function (ns, item) {
	if (!ns) {
		ns = dj_global
	}
	for (var x in ns) {
		if (ns[x] === item) {
			return String(x)
		}
	}
	return null
};
dojo.lang.shallowCopy = function (obj, deep) {
	var i, ret;
	if (obj === null) {
		return null
	}
	if (dojo.lang.isObject(obj)) {
		ret = new obj.constructor();
		for (i in obj) {
			if (dojo.lang.isUndefined(ret[i])) {
				ret[i] = deep ? dojo.lang.shallowCopy(obj[i], deep) : obj[i]
			}
		}
	} else {
		if (dojo.lang.isArray(obj)) {
			ret = [];
			for (i = 0; i < obj.length; i++) {
				ret[i] = deep ? dojo.lang.shallowCopy(obj[i], deep) : obj[i]
			}
		} else {
			ret = obj
		}
	}
	return ret
};
dojo.lang.firstValued = function () {
	for (var i = 0; i < arguments.length; i++) {
		if (typeof arguments[i] != 'undefined') {
			return arguments[i]
		}
	}
	return undefined
};
dojo.lang.getObjPathValue = function (_138, _139, _13a) {
	with (dojo.parseObjPath(_138, _139, _13a)) {
		return dojo.evalProp(prop, obj, _13a)
	}
};
dojo.lang.setObjPathValue = function (_13b, _13c, _13d, _13e) {
	dojo.deprecated('dojo.lang.setObjPathValue', 'use dojo.parseObjPath and the \'=\' operator', '0.6');
	if (arguments.length < 4) {
		_13e = true
	}
	with (dojo.parseObjPath(_13b, _13d, _13e)) {
		if (obj && (_13e || (prop in obj))) {
			obj[prop] = _13c
		}
	}
};
dojo.provide('dojo.io.common');
dojo.io.transports = [];
dojo.io.hdlrFuncNames = ['load', 'error', 'timeout'];
dojo.io.Request = function (url, _140, _141, _142) {
	if ((arguments.length == 1) && (arguments[0].constructor == Object)) {
		this.fromKwArgs(arguments[0])
	} else {
		this.url = url;
		if (_140) {
			this.mimetype = _140
		}
		if (_141) {
			this.transport = _141
		}
		if (arguments.length >= 4) {
			this.changeUrl = _142
		}
	}
};
dojo.lang.extend(dojo.io.Request, {
	url: '',
	mimetype: 'text/plain',
	method: 'GET',
	content: undefined,
	transport: undefined,
	changeUrl: undefined,
	formNode: undefined,
	sync: false,
	bindSuccess: false,
	useCache: false,
	preventCache: false,
	jsonFilter: function (_143) {
		if ((this.mimetype == 'text/json-comment-filtered') || (this.mimetype == 'application/json-comment-filtered')) {
			var _144 = _143.indexOf('/*');
			var _145 = _143.lastIndexOf('*/');
			if ((_144 == -1) || (_145 == -1)) {
				dojo.debug('your JSON wasn\'t comment filtered!');
				return ''
			}
			return _143.substring(_144 + 2, _145)
		}
		dojo.debug('please consider using a mimetype of text/json-comment-filtered to avoid potential security issues with JSON endpoints');
		return _143
	},
	load: function (type, data, _148, _149) {
	},
	error: function (type, _14b, _14c, _14d) {
	},
	timeout: function (type, _14f, _150, _151) {
	},
	handle: function (type, data, _154, _155) {
	},
	timeoutSeconds: 0,
	abort: function () {
	},
	fromKwArgs: function (_156) {
		if (_156['url']) {
			_156.url = _156.url.toString()
		}
		if (_156['formNode']) {
			_156.formNode = dojo.byId(_156.formNode)
		}
		if (!_156['method'] && _156['formNode'] && _156['formNode'].method) {
			_156.method = _156['formNode'].method
		}
		if (!_156['handle'] && _156['handler']) {
			_156.handle = _156.handler
		}
		if (!_156['load'] && _156['loaded']) {
			_156.load = _156.loaded
		}
		if (!_156['changeUrl'] && _156['changeURL']) {
			_156.changeUrl = _156.changeURL
		}
		_156.encoding = dojo.lang.firstValued(_156['encoding'], djConfig['bindEncoding'], '');
		_156.sendTransport = dojo.lang.firstValued(_156['sendTransport'], djConfig['ioSendTransport'], false);
		var _157 = dojo.lang.isFunction;
		for (var x = 0; x < dojo.io.hdlrFuncNames.length; x++) {
			var fn = dojo.io.hdlrFuncNames[x];
			if (_156[fn] && _157(_156[fn])) {
				continue
			}
			if (_156['handle'] && _157(_156['handle'])) {
				_156[fn] = _156.handle
			}
		}
		dojo.lang.mixin(this, _156)
	}
});
dojo.io.Error = function (msg, type, num) {
	this.message = msg;
	this.type = type || 'unknown';
	this.number = num || 0
};
dojo.io.transports.addTransport = function (name) {
	this.push(name);
	this[name] = dojo.io[name]
};
dojo.io.bind = function (_15e) {
	if (!(_15e instanceof dojo.io.Request)) {
		try {
			_15e = new dojo.io.Request(_15e)
		} catch (e) {
			dojo.debug(e)
		}
	}
	var _15f = '';
	if (_15e['transport']) {
		_15f = _15e['transport'];
		if (!this[_15f]) {
			dojo.io.sendBindError(_15e, 'No dojo.io.bind() transport with name \'' + _15e['transport'] + '\'.');
			return _15e
		}
		if (!this[_15f].canHandle(_15e)) {
			dojo.io.sendBindError(_15e, 'dojo.io.bind() transport with name \''
				+ _15e['transport']
				+ '\' cannot handle this type of request.');
			return _15e
		}
	} else {
		for (var x = 0; x < dojo.io.transports.length; x++) {
			var tmp = dojo.io.transports[x];
			if ((this[tmp]) && (this[tmp].canHandle(_15e))) {
				_15f = tmp;
				break
			}
		}
		if (_15f == '') {
			dojo.io.sendBindError(_15e, 'None of the loaded transports for dojo.io.bind()' + ' can handle the request.');
			return _15e
		}
	}
	this[_15f].bind(_15e);
	_15e.bindSuccess = true;
	return _15e
};
dojo.io.sendBindError = function (_162, _163) {
	if ((typeof _162.error == 'function' || typeof _162.handle == 'function') && (typeof setTimeout
		== 'function'
		|| typeof setTimeout
		== 'object')) {
		var _164 = new dojo.io.Error(_163);
		setTimeout(function () {
			_162[(typeof _162.error == 'function') ? 'error' : 'handle']('error', _164, null, _162)
		}, 50)
	} else {
		dojo.raise(_163)
	}
};
dojo.io.queueBind = function (_165) {
	if (!(_165 instanceof dojo.io.Request)) {
		try {
			_165 = new dojo.io.Request(_165)
		} catch (e) {
			dojo.debug(e)
		}
	}
	var _166 = _165.load;
	_165.load = function () {
		dojo.io._queueBindInFlight = false;
		var ret = _166.apply(this, arguments);
		dojo.io._dispatchNextQueueBind();
		return ret
	};
	var _168 = _165.error;
	_165.error = function () {
		dojo.io._queueBindInFlight = false;
		var ret = _168.apply(this, arguments);
		dojo.io._dispatchNextQueueBind();
		return ret
	};
	dojo.io._bindQueue.push(_165);
	dojo.io._dispatchNextQueueBind();
	return _165
};
dojo.io._dispatchNextQueueBind = function () {
	if (!dojo.io._queueBindInFlight) {
		dojo.io._queueBindInFlight = true;
		if (dojo.io._bindQueue.length > 0) {
			dojo.io.bind(dojo.io._bindQueue.shift())
		} else {
			dojo.io._queueBindInFlight = false
		}
	}
};
dojo.io._bindQueue = [];
dojo.io._queueBindInFlight = false;
dojo.io.argsFromMap = function (map, _16b, last) {
	var enc = /utf/i.test(_16b || '') ? encodeURIComponent : dojo.string.encodeAscii;
	var _16e = [];
	var _16f = {};
	for (var name in map) {
		var _171 = function (elt) {
			var val = enc(name) + '=' + enc(elt);
			_16e[(last == name) ? 'push' : 'unshift'](val)
		};
		if (!_16f[name]) {
			var _174 = map[name];
			if (dojo.lang.isArray(_174)) {
				dojo.lang.forEach(_174, _171)
			} else {
				_171(_174)
			}
		}
	}
	return _16e.join('&')
};
dojo.io.setIFrameSrc = function (_175, src, _177) {
	try {
		var r = dojo.render.html;
		if (!_177) {
			if (r.safari) {
				_175.location = src
			} else {
				frames[_175.name].location = src
			}
		} else {
			var idoc;
			if (r.ie) {
				idoc = _175.contentWindow.document
			} else {
				if (r.safari) {
					idoc = _175.document
				} else {
					idoc = _175.contentWindow
				}
			}
			if (!idoc) {
				_175.location = src;

			} else {
				idoc.location.replace(src)
			}
		}
	} catch (e) {
		dojo.debug(e);
		dojo.debug('setIFrameSrc: ' + e)
	}
};
dojo.provide('dojo.lang.array');
dojo.lang.mixin(dojo.lang, {
	has: function (obj, name) {
		try {
			return typeof obj[name] != 'undefined'
		} catch (e) {
			return false
		}
	}, isEmpty: function (obj) {
		if (dojo.lang.isObject(obj)) {
			var tmp = {};
			var _17e = 0;
			for (var x in obj) {
				if (obj[x] && (!tmp[x])) {
					_17e++;
					break
				}
			}
			return _17e == 0
		} else {
			if (dojo.lang.isArrayLike(obj) || dojo.lang.isString(obj)) {
				return obj.length == 0
			}
		}
	}, map: function (arr, obj, _182) {
		var _183 = dojo.lang.isString(arr);
		if (_183) {
			arr = arr.split('')
		}
		if (dojo.lang.isFunction(obj) && (!_182)) {
			_182 = obj;
			obj = dj_global
		} else {
			if (dojo.lang.isFunction(obj) && _182) {
				var _184 = obj;
				obj = _182;
				_182 = _184
			}
		}
		if (Array.map) {
			var _185 = Array.map(arr, _182, obj)
		} else {
			var _185 = [];
			for (var i = 0; i < arr.length; ++i) {
				_185.push(_182.call(obj, arr[i]))
			}
		}
		if (_183) {
			return _185.join('')
		} else {
			return _185
		}
	}, reduce: function (arr, _188, obj, _18a) {
		var _18b = _188;
		if (arguments.length == 2) {
			_18a = _188;
			_18b = arr[0];
			arr = arr.slice(1)
		} else {
			if (arguments.length == 3) {
				if (dojo.lang.isFunction(obj)) {
					_18a = obj;
					obj = null
				}
			} else {
				if (dojo.lang.isFunction(obj)) {
					var tmp = _18a;
					_18a = obj;
					obj = tmp
				}
			}
		}
		var ob = obj || dj_global;
		dojo.lang.map(arr, function (val) {
			_18b = _18a.call(ob, _18b, val)
		});
		return _18b
	}, forEach: function (_18f, _190, _191) {
		if (dojo.lang.isString(_18f)) {
			_18f = _18f.split('')
		}
		if (Array.forEach) {
			Array.forEach(_18f, _190, _191)
		} else {
			if (!_191) {
				_191 = dj_global
			}
			for (var i = 0, l = _18f.length; i < l; i++) {
				_190.call(_191, _18f[i], i, _18f)
			}
		}
	}, _everyOrSome: function (_194, arr, _196, _197) {
		if (dojo.lang.isString(arr)) {
			arr = arr.split('')
		}
		if (Array.every) {
			return Array[_194 ? 'every' : 'some'](arr, _196, _197)
		} else {
			if (!_197) {
				_197 = dj_global
			}
			for (var i = 0, l = arr.length; i < l; i++) {
				var _19a = _196.call(_197, arr[i], i, arr);
				if (_194 && !_19a) {
					return false
				} else {
					if ((!_194) && (_19a)) {
						return true
					}
				}
			}
			return Boolean(_194)
		}
	}, every: function (arr, _19c, _19d) {
		return this._everyOrSome(true, arr, _19c, _19d)
	}, some: function (arr, _19f, _1a0) {
		return this._everyOrSome(false, arr, _19f, _1a0)
	}, filter: function (arr, _1a2, _1a3) {
		var _1a4 = dojo.lang.isString(arr);
		if (_1a4) {
			arr = arr.split('')
		}
		var _1a5;
		if (Array.filter) {
			_1a5 = Array.filter(arr, _1a2, _1a3)
		} else {
			if (!_1a3) {
				if (arguments.length >= 3) {
					dojo.raise('thisObject doesn\'t exist!')
				}
				_1a3 = dj_global
			}
			_1a5 = [];
			for (var i = 0; i < arr.length; i++) {
				if (_1a2.call(_1a3, arr[i], i, arr)) {
					_1a5.push(arr[i])
				}
			}
		}
		if (_1a4) {
			return _1a5.join('')
		} else {
			return _1a5
		}
	}, unnest: function () {
		var out = [];
		for (var i = 0; i < arguments.length; i++) {
			if (dojo.lang.isArrayLike(arguments[i])) {
				var add = dojo.lang.unnest.apply(this, arguments[i]);
				out = out.concat(add)
			} else {
				out.push(arguments[i])
			}
		}
		return out
	}, toArray: function (_1aa, _1ab) {
		var _1ac = [];
		for (var i = _1ab || 0; i < _1aa.length; i++) {
			_1ac.push(_1aa[i])
		}
		return _1ac
	}
});
dojo.provide('dojo.lang.func');
dojo.lang.hitch = function (_1ae, _1af) {
	var args = [];
	for (var x = 2; x < arguments.length; x++) {
		args.push(arguments[x])
	}
	var fcn = (dojo.lang.isString(_1af) ? _1ae[_1af] : _1af) || function () {
	};
	return function () {
		var ta = args.concat([]);
		for (var x = 0; x < arguments.length; x++) {
			ta.push(arguments[x])
		}
		return fcn.apply(_1ae, ta)
	}
};
dojo.lang.anonCtr = 0;
dojo.lang.anon = {};
dojo.lang.nameAnonFunc = function (_1b5, _1b6, _1b7) {
	var nso = (_1b6 || dojo.lang.anon);
	if ((_1b7) || ((dj_global['djConfig']) && (djConfig['slowAnonFuncLookups'] == true))) {
		for (var x in nso) {
			try {
				if (nso[x] === _1b5) {
					return x
				}
			} catch (e) {
			}
		}
	}
	var ret = '__' + dojo.lang.anonCtr++;
	while (typeof nso[ret] != 'undefined') {
		ret = '__' + dojo.lang.anonCtr++
	}
	nso[ret] = _1b5;
	return ret
};
dojo.lang.forward = function (_1bb) {
	return function () {
		return this[_1bb].apply(this, arguments)
	}
};
dojo.lang.curry = function (_1bc, func) {
	var _1be = [];
	_1bc = _1bc || dj_global;
	if (dojo.lang.isString(func)) {
		func = _1bc[func]
	}
	for (var x = 2; x < arguments.length; x++) {
		_1be.push(arguments[x])
	}
	var _1c0 = (func['__preJoinArity'] || func.length) - _1be.length;

	function gather(_1c1, _1c2, _1c3) {
		var _1c4 = _1c3;
		var _1c5 = _1c2.slice(0);
		for (var x = 0; x < _1c1.length; x++) {
			_1c5.push(_1c1[x])
		}
		_1c3 = _1c3 - _1c1.length;
		if (_1c3 <= 0) {
			var res = func.apply(_1bc, _1c5);
			_1c3 = _1c4;
			return res
		} else {
			return function () {
				return gather(arguments, _1c5, _1c3)
			}
		}
	}

	return gather([], _1be, _1c0)
};
dojo.lang.curryArguments = function (_1c8, func, args, _1cb) {
	var _1cc = [];
	var x = _1cb || 0;
	for (x = _1cb; x < args.length; x++) {
		_1cc.push(args[x])
	}
	return dojo.lang.curry.apply(dojo.lang, [_1c8, func].concat(_1cc))
};
dojo.lang.tryThese = function () {
	for (var x = 0; x < arguments.length; x++) {
		try {
			if (typeof arguments[x] == 'function') {
				var ret = (arguments[x]());
				if (ret) {
					return ret
				}
			}
		} catch (e) {
			dojo.debug(e)
		}
	}
};
dojo.lang.delayThese = function (farr, cb, _1d2, _1d3) {
	if (!farr.length) {
		if (typeof _1d3 == 'function') {
			_1d3()
		}
		return
	}
	if ((typeof _1d2 == 'undefined') && (typeof cb == 'number')) {
		_1d2 = cb;
		cb = function () {
		}
	} else {
		if (!cb) {
			cb = function () {
			};
			if (!_1d2) {
				_1d2 = 0
			}
		}
	}
	setTimeout(function () {
		(farr.shift())();
		cb();
		dojo.lang.delayThese(farr, cb, _1d2, _1d3)
	}, _1d2)
};
dojo.provide('dojo.string.extras');
dojo.string.substituteParams = function (_1d4, hash) {
	var map = (typeof hash == 'object') ? hash : dojo.lang.toArray(arguments, 1);
	return _1d4.replace(/\%\{(\w+)\}/g, function (_1d7, key) {
		if (typeof (map[key]) != 'undefined' && map[key] != null) {
			return map[key]
		}
		dojo.raise('Substitution not found: ' + key)
	})
};
dojo.string.capitalize = function (str) {
	if (!dojo.lang.isString(str)) {
		return ''
	}
	if (arguments.length == 0) {
		str = this
	}
	var _1da = str.split(' ');
	for (var i = 0; i < _1da.length; i++) {
		_1da[i] = _1da[i].charAt(0).toUpperCase() + _1da[i].substring(1)
	}
	return _1da.join(' ')
};
dojo.string.isBlank = function (str) {
	if (!dojo.lang.isString(str)) {
		return true
	}
	return (dojo.string.trim(str).length == 0)
};
dojo.string.encodeAscii = function (str) {
	if (!dojo.lang.isString(str)) {
		return str
	}
	var ret = '';
	var _1df = escape(str);
	var _1e0, re = /%u([0-9A-F]{4})/i;
	while ((_1e0 = _1df.match(re))) {
		var num = Number('0x' + _1e0[1]);
		var _1e3 = escape('&#' + num + ';');
		ret += _1df.substring(0, _1e0.index) + _1e3;
		_1df = _1df.substring(_1e0.index + _1e0[0].length)
	}
	ret += _1df.replace(/\+/g, '%2B');
	return ret
};
dojo.string.escape = function (type, str) {
	var args = dojo.lang.toArray(arguments, 1);
	switch (type.toLowerCase()) {
		case 'xml':
		case 'html':
		case 'xhtml':
			return dojo.string.escapeXml.apply(this, args);
		case 'sql':
			return dojo.string.escapeSql.apply(this, args);
		case 'regexp':
		case 'regex':
			return dojo.string.escapeRegExp.apply(this, args);
		case 'javascript':
		case 'jscript':
		case 'js':
			return dojo.string.escapeJavaScript.apply(this, args);
		case 'ascii':
			return dojo.string.encodeAscii.apply(this, args);
		default:
			return str
	}
};
dojo.string.escapeXml = function (str, _1e8) {
	str = str.replace(/&/gm, '&amp;').replace(/</gm, '&lt;').replace(/>/gm, '&gt;').replace(/"/gm, '&quot;');
	if (!_1e8) {
		str = str.replace(/'/gm, '&#39;')
	}
	return str
};
dojo.string.escapeSql = function (str) {
	return str.replace(/'/gm, '\'\'')
};
dojo.string.escapeRegExp = function (str) {
	return str.replace(/\\/gm, '\\\\').replace(/([\f\b\n\t\r[\^$|?*+(){}])/gm, '\\$1')
};
dojo.string.escapeJavaScript = function (str) {
	return str.replace(/(["'\f\b\n\t\r])/gm, '\\$1')
};
dojo.string.escapeString = function (str) {
	return ('"'
		+ str.replace(/(["\\])/g, '\\$1')
		+ '"').replace(/[\f]/g, '\\f').replace(/[\b]/g, '\\b').replace(/[\n]/g, '\\n').replace(/[\t]/g, '\\t').replace(/[\r]/g, '\\r')
};
dojo.string.summary = function (str, len) {
	if (!len || str.length <= len) {
		return str
	}
	return str.substring(0, len).replace(/\.+$/, '') + '...'
};
dojo.string.endsWith = function (str, end, _1f1) {
	if (_1f1) {
		str = str.toLowerCase();
		end = end.toLowerCase()
	}
	if ((str.length - end.length) < 0) {
		return false
	}
	return str.lastIndexOf(end) == str.length - end.length
};
dojo.string.endsWithAny = function (str) {
	for (var i = 1; i < arguments.length; i++) {
		if (dojo.string.endsWith(str, arguments[i])) {
			return true
		}
	}
	return false
};
dojo.string.startsWith = function (str, _1f5, _1f6) {
	if (_1f6) {
		str = str.toLowerCase();
		_1f5 = _1f5.toLowerCase()
	}
	return str.indexOf(_1f5) == 0
};
dojo.string.startsWithAny = function (str) {
	for (var i = 1; i < arguments.length; i++) {
		if (dojo.string.startsWith(str, arguments[i])) {
			return true
		}
	}
	return false
};
dojo.string.has = function (str) {
	for (var i = 1; i < arguments.length; i++) {
		if (str.indexOf(arguments[i]) > -1) {
			return true
		}
	}
	return false
};
dojo.string.normalizeNewlines = function (text, _1fc) {
	if (_1fc == '\n') {
		text = text.replace(/\r\n/g, '\n');
		text = text.replace(/\r/g, '\n')
	} else {
		if (_1fc == '\r') {
			text = text.replace(/\r\n/g, '\r');
			text = text.replace(/\n/g, '\r')
		} else {
			text = text.replace(/([^\r])\n/g, '$1\r\n').replace(/\r([^\n])/g, '\r\n$1')
		}
	}
	return text
};
dojo.string.splitEscaped = function (str, _1fe) {
	var _1ff = [];
	for (var i = 0, _201 = 0; i < str.length; i++) {
		if (str.charAt(i) == '\\') {
			i++;
			continue
		}
		if (str.charAt(i) == _1fe) {
			_1ff.push(str.substring(_201, i));
			_201 = i + 1
		}
	}
	_1ff.push(str.substr(_201));
	return _1ff
};
dojo.provide('dojo.dom');
dojo.dom.ELEMENT_NODE = 1;
dojo.dom.ATTRIBUTE_NODE = 2;
dojo.dom.TEXT_NODE = 3;
dojo.dom.CDATA_SECTION_NODE = 4;
dojo.dom.ENTITY_REFERENCE_NODE = 5;
dojo.dom.ENTITY_NODE = 6;
dojo.dom.PROCESSING_INSTRUCTION_NODE = 7;
dojo.dom.COMMENT_NODE = 8;
dojo.dom.DOCUMENT_NODE = 9;
dojo.dom.DOCUMENT_TYPE_NODE = 10;
dojo.dom.DOCUMENT_FRAGMENT_NODE = 11;
dojo.dom.NOTATION_NODE = 12;
dojo.dom.dojoml = 'http://www.dojotoolkit.org/2004/dojoml';
dojo.dom.xmlns = {
	svg: 'http://www.w3.org/2000/svg',
	smil: 'http://www.w3.org/2001/SMIL20/',
	mml: 'http://www.w3.org/1998/Math/MathML',
	cml: 'http://www.xml-cml.org',
	xlink: 'http://www.w3.org/1999/xlink',
	xhtml: 'http://www.w3.org/1999/xhtml',
	xul: 'http://www.mozilla.org/keymaster/gatekeeper/there.is.only.xul',
	xbl: 'http://www.mozilla.org/xbl',
	fo: 'http://www.w3.org/1999/XSL/Format',
	xsl: 'http://www.w3.org/1999/XSL/Transform',
	xslt: 'http://www.w3.org/1999/XSL/Transform',
	xi: 'http://www.w3.org/2001/XInclude',
	xforms: 'http://www.w3.org/2002/01/xforms',
	saxon: 'http://icl.com/saxon',
	xalan: 'http://xml.apache.org/xslt',
	xsd: 'http://www.w3.org/2001/XMLSchema',
	dt: 'http://www.w3.org/2001/XMLSchema-datatypes',
	xsi: 'http://www.w3.org/2001/XMLSchema-instance',
	rdf: 'http://www.w3.org/1999/02/22-rdf-syntax-ns#',
	rdfs: 'http://www.w3.org/2000/01/rdf-schema#',
	dc: 'http://purl.org/dc/elements/1.1/',
	dcq: 'http://purl.org/dc/qualifiers/1.0',
	'soap-env': 'http://schemas.xmlsoap.org/soap/envelope/',
	wsdl: 'http://schemas.xmlsoap.org/wsdl/',
	AdobeExtensions: 'http://ns.adobe.com/AdobeSVGViewerExtensions/3.0/'
};
dojo.dom.isNode = function (wh) {
	if (typeof Element == 'function') {
		try {
			return wh instanceof Element
		} catch (e) {
		}
	} else {
		return wh && !isNaN(wh.nodeType)
	}
};
dojo.dom.getUniqueId = function () {
	var _203 = dojo.doc();
	do {
		var id = 'dj_unique_' + (++arguments.callee._idIncrement)
	} while (_203.getElementById(id));
	return id
};
dojo.dom.getUniqueId._idIncrement = 0;
dojo.dom.firstElement = dojo.dom.getFirstChildElement = function (_205, _206) {
	var node = _205.firstChild;
	while (node && node.nodeType != dojo.dom.ELEMENT_NODE) {
		node = node.nextSibling
	}
	if (_206 && node && node.tagName && node.tagName.toLowerCase() != _206.toLowerCase()) {
		node = dojo.dom.nextElement(node, _206)
	}
	return node
};
dojo.dom.lastElement = dojo.dom.getLastChildElement = function (_208, _209) {
	var node = _208.lastChild;
	while (node && node.nodeType != dojo.dom.ELEMENT_NODE) {
		node = node.previousSibling
	}
	if (_209 && node && node.tagName && node.tagName.toLowerCase() != _209.toLowerCase()) {
		node = dojo.dom.prevElement(node, _209)
	}
	return node
};
dojo.dom.nextElement = dojo.dom.getNextSiblingElement = function (node, _20c) {
	if (!node) {
		return null
	}
	do {
		node = node.nextSibling
	} while (node && node.nodeType != dojo.dom.ELEMENT_NODE);
	if (node && _20c && _20c.toLowerCase() != node.tagName.toLowerCase()) {
		return dojo.dom.nextElement(node, _20c)
	}
	return node
};
dojo.dom.prevElement = dojo.dom.getPreviousSiblingElement = function (node, _20e) {
	if (!node) {
		return null
	}
	if (_20e) {
		_20e = _20e.toLowerCase()
	}
	do {
		node = node.previousSibling
	} while (node && node.nodeType != dojo.dom.ELEMENT_NODE);
	if (node && _20e && _20e.toLowerCase() != node.tagName.toLowerCase()) {
		return dojo.dom.prevElement(node, _20e)
	}
	return node
};
dojo.dom.moveChildren = function (_20f, _210, trim) {
	var _212 = 0;
	if (trim) {
		while (_20f.hasChildNodes() && _20f.firstChild.nodeType == dojo.dom.TEXT_NODE) {
			_20f.removeChild(_20f.firstChild)
		}
		while (_20f.hasChildNodes() && _20f.lastChild.nodeType == dojo.dom.TEXT_NODE) {
			_20f.removeChild(_20f.lastChild)
		}
	}
	while (_20f.hasChildNodes()) {
		_210.appendChild(_20f.firstChild);
		_212++
	}
	return _212
};
dojo.dom.copyChildren = function (_213, _214, trim) {
	var _216 = _213.cloneNode(true);
	return this.moveChildren(_216, _214, trim)
};
dojo.dom.replaceChildren = function (node, _218) {
	var _219 = [];
	if (dojo.render.html.ie) {
		for (var i = 0; i < node.childNodes.length; i++) {
			_219.push(node.childNodes[i])
		}
	}
	dojo.dom.removeChildren(node);
	node.appendChild(_218);
	for (var i = 0; i < _219.length; i++) {
		dojo.dom.destroyNode(_219[i])
	}
};
dojo.dom.removeChildren = function (node) {
	var _21c = node.childNodes.length;
	while (node.hasChildNodes()) {
		dojo.dom.removeNode(node.firstChild)
	}
	return _21c
};
dojo.dom.replaceNode = function (node, _21e) {
	return node.parentNode.replaceChild(_21e, node)
};
dojo.dom.destroyNode = function (node) {
	if (node.parentNode) {
		node = dojo.dom.removeNode(node)
	}
	if (node.nodeType != 3) {
		if (dojo.evalObjPath('dojo.event.browser.clean', false)) {
			dojo.event.browser.clean(node)
		}
		if (dojo.render.html.ie) {
			node.outerHTML = ''
		}
	}
};
dojo.dom.removeNode = function (node) {
	if (node && node.parentNode) {
		return node.parentNode.removeChild(node)
	}
};
dojo.dom.getAncestors = function (node, _222, _223) {
	var _224 = [];
	var _225 = (_222 && (_222 instanceof Function || typeof _222 == 'function'));
	while (node) {
		if (!_225 || _222(node)) {
			_224.push(node)
		}
		if (_223 && _224.length > 0) {
			return _224[0]
		}
		node = node.parentNode
	}
	if (_223) {
		return null
	}
	return _224
};
dojo.dom.getAncestorsByTag = function (node, tag, _228) {
	tag = tag.toLowerCase();
	return dojo.dom.getAncestors(node, function (el) {
		return ((el.tagName) && (el.tagName.toLowerCase() == tag))
	}, _228)
};
dojo.dom.getFirstAncestorByTag = function (node, tag) {
	return dojo.dom.getAncestorsByTag(node, tag, true)
};
dojo.dom.isDescendantOf = function (node, _22d, _22e) {
	if (_22e && node) {
		node = node.parentNode
	}
	while (node) {
		if (node == _22d) {
			return true
		}
		node = node.parentNode
	}
	return false
};
dojo.dom.innerXML = function (node) {
	if (node.innerXML) {
		return node.innerXML
	} else {
		if (node.xml) {
			return node.xml
		} else {
			if (typeof XMLSerializer != 'undefined') {
				return (new XMLSerializer()).serializeToString(node)
			}
		}
	}
};
dojo.dom.createDocument = function () {
	var doc = null;
	var _231 = dojo.doc();
	if (!dj_undef('ActiveXObject')) {
		var _232 = ['MSXML2', 'Microsoft', 'MSXML', 'MSXML3'];
		for (var i = 0; i < _232.length; i++) {
			try {
				doc = new ActiveXObject(_232[i] + '.XMLDOM')
			} catch (e) {
			}
			if (doc) {
				break
			}
		}
	} else {
		if ((_231.implementation) && (_231.implementation.createDocument)) {
			doc = _231.implementation.createDocument('', '', null)
		}
	}
	return doc
};
dojo.dom.createDocumentFromText = function (str, _235) {
	if (!_235) {
		_235 = 'text/xml'
	}
	if (!dj_undef('DOMParser')) {
		var _236 = new DOMParser();
		return _236.parseFromString(str, _235)
	} else {
		if (!dj_undef('ActiveXObject')) {
			var _237 = dojo.dom.createDocument();
			if (_237) {
				_237.async = false;
				_237.loadXML(str);
				return _237
			} else {
				dojo.debug('toXml didn\'t work?')
			}
		} else {
			var _238 = dojo.doc();
			if (_238.createElement) {
				var tmp = _238.createElement('xml');
				tmp.innerHTML = str;
				if (_238.implementation && _238.implementation.createDocument) {
					var _23a = _238.implementation.createDocument('foo', '', null);
					for (var i = 0; i < tmp.childNodes.length; i++) {
						_23a.importNode(tmp.childNodes.item(i), true)
					}
					return _23a
				}
				return ((tmp.document) && (tmp.document.firstChild ? tmp.document.firstChild : tmp))
			}
		}
	}
	return null
};
dojo.dom.prependChild = function (node, _23d) {
	if (_23d.firstChild) {
		_23d.insertBefore(node, _23d.firstChild)
	} else {
		_23d.appendChild(node)
	}
	return true
};
dojo.dom.insertBefore = function (node, ref, _240) {
	if ((_240 != true) && (node === ref || node.nextSibling === ref)) {
		return false
	}
	var _241 = ref.parentNode;
	_241.insertBefore(node, ref);
	return true
};
dojo.dom.insertAfter = function (node, ref, _244) {
	var pn = ref.parentNode;
	if (ref == pn.lastChild) {
		if ((_244 != true) && (node === ref)) {
			return false
		}
		pn.appendChild(node)
	} else {
		return this.insertBefore(node, ref.nextSibling, _244)
	}
	return true
};
dojo.dom.insertAtPosition = function (node, ref, _248) {
	if ((!node) || (!ref) || (!_248)) {
		return false
	}
	switch (_248.toLowerCase()) {
		case 'before':
			return dojo.dom.insertBefore(node, ref);
		case 'after':
			return dojo.dom.insertAfter(node, ref);
		case 'first':
			if (ref.firstChild) {
				return dojo.dom.insertBefore(node, ref.firstChild)
			} else {
				ref.appendChild(node);
				return true
			}
			break;
		default:
			ref.appendChild(node);
			return true
	}
};
dojo.dom.insertAtIndex = function (node, _24a, _24b) {
	var _24c = _24a.childNodes;
	if (!_24c.length || _24c.length == _24b) {
		_24a.appendChild(node);
		return true
	}
	if (_24b == 0) {
		return dojo.dom.prependChild(node, _24a)
	}
	return dojo.dom.insertAfter(node, _24c[_24b - 1])
};
dojo.dom.textContent = function (node, text) {
	if (arguments.length > 1) {
		var _24f = dojo.doc();
		dojo.dom.replaceChildren(node, _24f.createTextNode(text));
		return text
	} else {
		if (node.textContent != undefined) {
			return node.textContent
		}
		var _250 = '';
		if (node == null) {
			return _250
		}
		for (var i = 0; i < node.childNodes.length; i++) {
			switch (node.childNodes[i].nodeType) {
				case 1:
				case 5:
					_250 += dojo.dom.textContent(node.childNodes[i]);
					break;
				case 3:
				case 2:
				case 4:
					_250 += node.childNodes[i].nodeValue;
					break;
				default:
					break
			}
		}
		return _250
	}
};
dojo.dom.hasParent = function (node) {
	return Boolean(node && node.parentNode && dojo.dom.isNode(node.parentNode))
};
dojo.dom.isTag = function (node) {
	if (node && node.tagName) {
		for (var i = 1; i < arguments.length; i++) {
			if (node.tagName == String(arguments[i])) {
				return String(arguments[i])
			}
		}
	}
	return ''
};
dojo.dom.setAttributeNS = function (elem, _256, _257, _258) {
	if (elem == null || ((elem == undefined) && (typeof elem == 'undefined'))) {
		dojo.raise('No element given to dojo.dom.setAttributeNS')
	}
	if (!((elem.setAttributeNS == undefined) && (typeof elem.setAttributeNS == 'undefined'))) {
		elem.setAttributeNS(_256, _257, _258)
	} else {
		var _259 = elem.ownerDocument;
		var _25a = _259.createNode(2, _257, _256);
		_25a.nodeValue = _258;
		elem.setAttributeNode(_25a)
	}
};
dojo.provide('dojo.undo.browser');
try {
	if ((!djConfig['preventBackButtonFix']) && (!dojo.hostenv.post_load_)) {
		document.write('<iframe style=\'border: 0px; width: 1px; height: 1px; position: absolute; bottom: 0px; right: 0px; visibility: visible;\' name=\'djhistory\' id=\'djhistory\' src=\''
			+ (djConfig['dojoIframeHistoryUrl'] || dojo.hostenv.getBaseScriptUri() + 'iframe_history.html')
			+ '\'></iframe>')
	}
} catch (e) {
}
if (dojo.render.html.opera) {
	dojo.debug('Opera is not supported with dojo.undo.browser, so back/forward detection will not work.')
}
dojo.undo.browser = {
	initialHref: (!dj_undef('window')) ? window.location.href : '',
	initialHash: (!dj_undef('window')) ? window.location.hash : '',
	moveForward: false,
	historyStack: [],
	forwardStack: [],
	historyIframe: null,
	bookmarkAnchor: null,
	locationTimer: null,
	setInitialState: function (args) {
		this.initialState = this._createState(this.initialHref, args, this.initialHash)
	},
	addToHistory: function (args) {
		this.forwardStack = [];
		var hash = null;
		var url = null;
		if (!this.historyIframe) {
			if (djConfig['useXDomain'] && !djConfig['dojoIframeHistoryUrl']) {
				dojo.debug('dojo.undo.browser: When using cross-domain Dojo builds,'
					+ ' please save iframe_history.html to your domain and set djConfig.dojoIframeHistoryUrl'
					+ ' to the path on your domain to iframe_history.html')
			}
			this.historyIframe = window.frames['djhistory']
		}
		if (!this.bookmarkAnchor) {
			this.bookmarkAnchor = document.createElement('a');
			dojo.body().appendChild(this.bookmarkAnchor);
			this.bookmarkAnchor.style.display = 'none'
		}
		if (args['changeUrl']) {
			hash = '#' + ((args['changeUrl'] !== true) ? args['changeUrl'] : (new Date()).getTime());
			if (this.historyStack.length == 0 && this.initialState.urlHash == hash) {
				this.initialState = this._createState(url, args, hash);
				return
			} else {
				if (this.historyStack.length > 0 && this.historyStack[this.historyStack.length - 1].urlHash == hash) {
					this.historyStack[this.historyStack.length - 1] = this._createState(url, args, hash);
					return
				}
			}
			this.changingUrl = true;
			setTimeout('window.location.href = \'' + hash + '\'; dojo.undo.browser.changingUrl = false;', 1);
			this.bookmarkAnchor.href = hash;
			if (dojo.render.html.ie) {
				url = this._loadIframeHistory();
				var _25f = args['back'] || args['backButton'] || args['handle'];
				var tcb = function (_261) {
					if (window.location.hash != '') {
						setTimeout('window.location.href = \'' + hash + '\';', 1)
					}
					_25f.apply(this, [_261])
				};
				if (args['back']) {
					args.back = tcb
				} else {
					if (args['backButton']) {
						args.backButton = tcb
					} else {
						if (args['handle']) {
							args.handle = tcb
						}
					}
				}
				var _262 = args['forward'] || args['forwardButton'] || args['handle'];
				var tfw = function (_264) {
					if (window.location.hash != '') {
						window.location.href = hash
					}
					if (_262) {
						_262.apply(this, [_264])
					}
				};
				if (args['forward']) {
					args.forward = tfw
				} else {
					if (args['forwardButton']) {
						args.forwardButton = tfw
					} else {
						if (args['handle']) {
							args.handle = tfw
						}
					}
				}
			} else {
				if (dojo.render.html.moz) {
					if (!this.locationTimer) {
						this.locationTimer = setInterval('dojo.undo.browser.checkLocation();', 200)
					}
				}
			}
		} else {
			url = this._loadIframeHistory()
		}
		this.historyStack.push(this._createState(url, args, hash))
	},
	checkLocation: function () {
		if (!this.changingUrl) {
			var hsl = this.historyStack.length;
			if ((window.location.hash == this.initialHash || window.location.href == this.initialHref) && (hsl == 1)) {
				this.handleBackButton();
				return
			}
			if (this.forwardStack.length > 0) {
				if (this.forwardStack[this.forwardStack.length - 1].urlHash == window.location.hash) {
					this.handleForwardButton();
					return
				}
			}
			if ((hsl >= 2) && (this.historyStack[hsl - 2])) {
				if (this.historyStack[hsl - 2].urlHash == window.location.hash) {
					this.handleBackButton();

				}
			}
		}
	},
	iframeLoaded: function (evt, _267) {
		if (!dojo.render.html.opera) {
			var _268 = this._getUrlQuery(_267.href);
			if (_268 == null) {
				if (this.historyStack.length == 1) {
					this.handleBackButton()
				}
				return
			}
			if (this.moveForward) {
				this.moveForward = false;
				return
			}
			if (this.historyStack.length >= 2 && _268 == this._getUrlQuery(this.historyStack[this.historyStack.length
			- 2].url)) {
				this.handleBackButton()
			} else {
				if (this.forwardStack.length > 0 && _268 == this._getUrlQuery(this.forwardStack[this.forwardStack.length
				- 1].url)) {
					this.handleForwardButton()
				}
			}
		}
	},
	handleBackButton: function () {
		var _269 = this.historyStack.pop();
		if (!_269) {
			return
		}
		var last = this.historyStack[this.historyStack.length - 1];
		if (!last && this.historyStack.length == 0) {
			last = this.initialState
		}
		if (last) {
			if (last.kwArgs['back']) {
				last.kwArgs['back']()
			} else {
				if (last.kwArgs['backButton']) {
					last.kwArgs['backButton']()
				} else {
					if (last.kwArgs['handle']) {
						last.kwArgs.handle('back')
					}
				}
			}
		}
		this.forwardStack.push(_269)
	},
	handleForwardButton: function () {
		var last = this.forwardStack.pop();
		if (!last) {
			return
		}
		if (last.kwArgs['forward']) {
			last.kwArgs.forward()
		} else {
			if (last.kwArgs['forwardButton']) {
				last.kwArgs.forwardButton()
			} else {
				if (last.kwArgs['handle']) {
					last.kwArgs.handle('forward')
				}
			}
		}
		this.historyStack.push(last)
	},
	_createState: function (url, args, hash) {
		return {'url': url, 'kwArgs': args, 'urlHash': hash}
	},
	_getUrlQuery: function (url) {
		var _270 = url.split('?');
		if (_270.length < 2) {
			return null
		} else {
			return _270[1]
		}
	},
	_loadIframeHistory: function () {
		var url = (djConfig['dojoIframeHistoryUrl'] || dojo.hostenv.getBaseScriptUri() + 'iframe_history.html')
			+ '?'
			+ (new Date()).getTime();
		this.moveForward = true;
		dojo.io.setIFrameSrc(this.historyIframe, url, false);
		return url
	}
};
dojo.provide('dojo.io.BrowserIO');
if (!dj_undef('window')) {
	dojo.io.checkChildrenForFile = function (node) {
		var _273 = false;
		var _274 = node.getElementsByTagName('input');
		dojo.lang.forEach(_274, function (_275) {
			if (_273) {
				return
			}
			if (_275.getAttribute('type') == 'file') {
				_273 = true
			}
		});
		return _273
	};
	dojo.io.formHasFile = function (_276) {
		return dojo.io.checkChildrenForFile(_276)
	};
	dojo.io.updateNode = function (node, _278) {
		node = dojo.byId(node);
		var args = _278;
		if (dojo.lang.isString(_278)) {
			args = {url: _278}
		}
		args.mimetype = 'text/html';
		args.load = function (t, d, e) {
			while (node.firstChild) {
				dojo.dom.destroyNode(node.firstChild)
			}
			node.innerHTML = d
		};
		dojo.io.bind(args)
	};
	dojo.io.formFilter = function (node) {
		var type = (node.type || '').toLowerCase();
		return !node.disabled && node.name && !dojo.lang.inArray(['file', 'submit', 'image', 'reset', 'button'], type)
	};
	dojo.io.encodeForm = function (_27f, _280, _281) {
		if ((!_27f) || (!_27f.tagName) || (!_27f.tagName.toLowerCase() == 'form')) {
			dojo.raise('Attempted to encode a non-form element.')
		}
		if (!_281) {
			_281 = dojo.io.formFilter
		}
		var enc = /utf/i.test(_280 || '') ? encodeURIComponent : dojo.string.encodeAscii;
		var _283 = [];
		for (var i = 0; i < _27f.elements.length; i++) {
			var elm = _27f.elements[i];
			if (!elm || elm.tagName.toLowerCase() == 'fieldset' || !_281(elm)) {
				continue
			}
			var name = enc(elm.name);
			var type = elm.type.toLowerCase();
			if (type == 'select-multiple') {
				for (var j = 0; j < elm.options.length; j++) {
					if (elm.options[j].selected) {
						_283.push(name + '=' + enc(elm.options[j].value))
					}
				}
			} else {
				if (dojo.lang.inArray(['radio', 'checkbox'], type)) {
					if (elm.checked) {
						_283.push(name + '=' + enc(elm.value))
					}
				} else {
					_283.push(name + '=' + enc(elm.value))
				}
			}
		}
		var _289 = _27f.getElementsByTagName('input');
		for (var i = 0; i < _289.length; i++) {
			var _28a = _289[i];
			if (_28a.type.toLowerCase() == 'image' && _28a.form == _27f && _281(_28a)) {
				var name = enc(_28a.name);
				_283.push(name + '=' + enc(_28a.value));
				_283.push(name + '.x=0');
				_283.push(name + '.y=0')
			}
		}
		return _283.join('&') + '&'
	};
	dojo.io.FormBind = function (args) {
		this.bindArgs = {};
		if (args && args.formNode) {
			this.init(args)
		} else {
			if (args) {
				this.init({formNode: args})
			}
		}
	};
	dojo.lang.extend(dojo.io.FormBind, {
		form: null, bindArgs: null, clickedButton: null, init: function (args) {
			var form = dojo.byId(args.formNode);
			if (!form || !form.tagName || form.tagName.toLowerCase() != 'form') {
				throw new Error('FormBind: Couldn\'t apply, invalid form')
			} else {
				if (this.form == form) {
					return
				} else {
					if (this.form) {
						throw new Error('FormBind: Already applied to a form')
					}
				}
			}
			dojo.lang.mixin(this.bindArgs, args);
			this.form = form;
			this.connect(form, 'onsubmit', 'submit');
			for (var i = 0; i < form.elements.length; i++) {
				var node = form.elements[i];
				if (node && node.type && dojo.lang.inArray(['submit', 'button'], node.type.toLowerCase())) {
					this.connect(node, 'onclick', 'click')
				}
			}
			var _290 = form.getElementsByTagName('input');
			for (var i = 0; i < _290.length; i++) {
				var _291 = _290[i];
				if (_291.type.toLowerCase() == 'image' && _291.form == form) {
					this.connect(_291, 'onclick', 'click')
				}
			}
		}, onSubmit: function (form) {
			return true
		}, submit: function (e) {
			e.preventDefault();
			if (this.onSubmit(this.form)) {
				dojo.io.bind(dojo.lang.mixin(this.bindArgs, {formFilter: dojo.lang.hitch(this, 'formFilter')}))
			}
		}, click: function (e) {
			var node = e.currentTarget;
			if (node.disabled) {
				return
			}
			this.clickedButton = node
		}, formFilter: function (node) {
			var type = (node.type || '').toLowerCase();
			var _298 = false;
			if (node.disabled || !node.name) {
				_298 = false
			} else {
				if (dojo.lang.inArray(['submit', 'button', 'image'], type)) {
					if (!this.clickedButton) {
						this.clickedButton = node
					}
					_298 = node == this.clickedButton
				} else {
					_298 = !dojo.lang.inArray(['file', 'submit', 'reset', 'button'], type)
				}
			}
			return _298
		}, connect: function (_299, _29a, _29b) {
			if (dojo.evalObjPath('dojo.event.connect')) {
				dojo.event.connect(_299, _29a, this, _29b)
			} else {
				var fcn = dojo.lang.hitch(this, _29b);
				_299[_29a] = function (e) {
					if (!e) {
						e = window.event
					}
					if (!e.currentTarget) {
						e.currentTarget = e.srcElement
					}
					if (!e.preventDefault) {
						e.preventDefault = function () {
							window.event.returnValue = false
						}
					}
					fcn(e)
				}
			}
		}
	});
	dojo.io.XMLHTTPTransport = new function () {
		var _29e = this;
		var _29f = {};
		this.useCache = false;
		this.preventCache = false;

		function getCacheKey(url, _2a1, _2a2) {
			return url + '|' + _2a1 + '|' + _2a2.toLowerCase()
		}

		function addToCache(url, _2a4, _2a5, http) {
			_29f[getCacheKey(url, _2a4, _2a5)] = http
		}

		function getFromCache(url, _2a8, _2a9) {
			return _29f[getCacheKey(url, _2a8, _2a9)]
		}

		this.clearCache = function () {
			_29f = {}
		};

		function doLoad(_2aa, http, url, _2ad, _2ae) {
			if (((http.status >= 200) && (http.status < 300))
				|| (http.status == 304)
				|| (http.status == 1223)
				|| (location.protocol == 'file:' && (http.status == 0 || http.status == undefined))
				|| (location.protocol == 'chrome:' && (http.status == 0 || http.status == undefined))) {
				var ret;
				if (_2aa.method.toLowerCase() == 'head') {
					var _2b0 = http.getAllResponseHeaders();
					ret = {};
					ret.toString = function () {
						return _2b0
					};
					var _2b1 = _2b0.split(/[\r\n]+/g);
					for (var i = 0; i < _2b1.length; i++) {
						var pair = _2b1[i].match(/^([^:]+)\s*:\s*(.+)$/i);
						if (pair) {
							ret[pair[1]] = pair[2]
						}
					}
				} else {
					if (_2aa.mimetype == 'text/javascript') {
						try {
							ret = dj_eval(http.responseText)
						} catch (e) {
							dojo.debug(e);
							dojo.debug(http.responseText);
							ret = null
						}
					} else {
						if (_2aa.mimetype.substr(0, 9) == 'text/json' || _2aa.mimetype.substr(0, 16) == 'application/json') {
							try {
								ret = dj_eval('(' + _2aa.jsonFilter(http.responseText) + ')')
							} catch (e) {
								dojo.debug(e);
								dojo.debug(http.responseText);
								ret = false
							}
						} else {
							if ((_2aa.mimetype == 'application/xml') || (_2aa.mimetype == 'text/xml')) {
								ret = http.responseXML;
								if (!ret || typeof ret == 'string' || !http.getResponseHeader('Content-Type')) {
									ret = dojo.dom.createDocumentFromText(http.responseText)
								}
							} else {
								ret = http.responseText
							}
						}
					}
				}
				if (_2ae) {
					addToCache(url, _2ad, _2aa.method, http)
				}
				_2aa[(typeof _2aa.load == 'function') ? 'load' : 'handle']('load', ret, http, _2aa)
			} else {
				var _2b4 = new dojo.io.Error('XMLHttpTransport Error: ' + http.status + ' ' + http.statusText);
				_2aa[(typeof _2aa.error == 'function') ? 'error' : 'handle']('error', _2b4, http, _2aa)
			}
		}

		function setHeaders(http, _2b6) {
			if (_2b6['headers']) {
				for (var _2b7 in _2b6['headers']) {
					if (_2b7.toLowerCase() == 'content-type' && !_2b6['contentType']) {
						_2b6['contentType'] = _2b6['headers'][_2b7]
					} else {
						http.setRequestHeader(_2b7, _2b6['headers'][_2b7])
					}
				}
			}
		}

		this.inFlight = [];
		this.inFlightTimer = null;
		this.startWatchingInFlight = function () {
			if (!this.inFlightTimer) {
				this.inFlightTimer = setTimeout('dojo.io.XMLHTTPTransport.watchInFlight();', 10)
			}
		};
		this.watchInFlight = function () {
			var now = null;
			if (!dojo.hostenv._blockAsync && !_29e._blockAsync) {
				for (var x = this.inFlight.length - 1; x >= 0; x--) {
					try {
						var tif = this.inFlight[x];
						if (!tif || tif.http._aborted || !tif.http.readyState) {
							this.inFlight.splice(x, 1);
							continue
						}
						if (4 == tif.http.readyState) {
							this.inFlight.splice(x, 1);
							doLoad(tif.req, tif.http, tif.url, tif.query, tif.useCache)
						} else {
							if (tif.startTime) {
								if (!now) {
									now = (new Date()).getTime()
								}
								if (tif.startTime + (tif.req.timeoutSeconds * 1000) < now) {
									if (typeof tif.http.abort == 'function') {
										tif.http.abort()
									}
									this.inFlight.splice(x, 1);
									tif.req[(typeof tif.req.timeout
										== 'function') ? 'timeout' : 'handle']('timeout', null, tif.http, tif.req)
								}
							}
						}
					} catch (e) {
						try {
							var _2bb = new dojo.io.Error('XMLHttpTransport.watchInFlight Error: ' + e);
							tif.req[(typeof tif.req.error == 'function') ? 'error' : 'handle']('error', _2bb, tif.http, tif.req)
						} catch (e2) {
							dojo.debug('XMLHttpTransport error callback failed: ' + e2)
						}
					}
				}
			}
			clearTimeout(this.inFlightTimer);
			if (this.inFlight.length == 0) {
				this.inFlightTimer = null;
				return
			}
			this.inFlightTimer = setTimeout('dojo.io.XMLHTTPTransport.watchInFlight();', 10)
		};
		var _2bc = dojo.hostenv.getXmlhttpObject() ? true : false;
		this.canHandle = function (_2bd) {
			var mlc = _2bd['mimetype'].toLowerCase() || '';
			return _2bc
				&& ((dojo.lang.inArray(['text/plain', 'text/html', 'application/xml', 'text/xml', 'text/javascript'], mlc))
					|| (mlc.substr(0, 9) == 'text/json' || mlc.substr(0, 16) == 'application/json'))
				&& !(_2bd['formNode'] && dojo.io.formHasFile(_2bd['formNode']))
		};
		this.multipartBoundary = '45309FFF-BD65-4d50-99C9-36986896A96F';
		this.bind = function (_2bf) {
			if (!_2bf['url']) {
				if (!_2bf['formNode']
					&& (_2bf['backButton'] || _2bf['back'] || _2bf['changeUrl'] || _2bf['watchForURL'])
					&& (!djConfig.preventBackButtonFix)) {
					dojo.deprecated('Using dojo.io.XMLHTTPTransport.bind() to add to browser history without doing an IO request', 'Use dojo.undo.browser.addToHistory() instead.', '0.4');
					dojo.undo.browser.addToHistory(_2bf);
					return true
				}
			}
			var url = _2bf.url;
			var _2c1 = '';
			if (_2bf['formNode']) {
				var ta = _2bf.formNode.getAttribute('action');
				if ((ta) && (!_2bf['url'])) {
					url = ta
				}
				var tp = _2bf.formNode.getAttribute('method');
				if ((tp) && (!_2bf['method'])) {
					_2bf.method = tp
				}
				_2c1 += dojo.io.encodeForm(_2bf.formNode, _2bf.encoding, _2bf['formFilter'])
			}
			if (url.indexOf('#') > -1) {
				dojo.debug('Warning: dojo.io.bind: stripping hash values from url:', url);
				url = url.split('#')[0]
			}
			if (_2bf['file']) {
				_2bf.method = 'post'
			}
			if (!_2bf['method']) {
				_2bf.method = 'get'
			}
			if (_2bf.method.toLowerCase() == 'get') {
				_2bf.multipart = false
			} else {
				if (_2bf['file']) {
					_2bf.multipart = true
				} else {
					if (!_2bf['multipart']) {
						_2bf.multipart = false
					}
				}
			}
			if (_2bf['backButton'] || _2bf['back'] || _2bf['changeUrl']) {
				dojo.undo.browser.addToHistory(_2bf)
			}
			var _2c4 = _2bf['content'] || {};
			if (_2bf.sendTransport) {
				_2c4['dojo.transport'] = 'xmlhttp'
			}
			do {
				if (_2bf.postContent) {
					_2c1 = _2bf.postContent;
					break
				}
				if (_2c4) {
					_2c1 += dojo.io.argsFromMap(_2c4, _2bf.encoding)
				}
				if (_2bf.method.toLowerCase() == 'get' || !_2bf.multipart) {
					break
				}
				var t = [];
				if (_2c1.length) {
					var q = _2c1.split('&');
					for (var i = 0; i < q.length; ++i) {
						if (q[i].length) {
							var p = q[i].split('=');
							t.push('--' + this.multipartBoundary, 'Content-Disposition: form-data; name="' + p[0] + '"', '', p[1])
						}
					}
				}
				if (_2bf.file) {
					if (dojo.lang.isArray(_2bf.file)) {
						for (var i = 0; i < _2bf.file.length; ++i) {
							var o = _2bf.file[i];
							t.push('--' + this.multipartBoundary, 'Content-Disposition: form-data; name="'
								+ o.name
								+ '"; filename="'
								+ ('fileName' in o ? o.fileName : o.name)
								+ '"', 'Content-Type: ' + ('contentType'
							in o ? o.contentType : 'application/octet-stream'), '', o.content)
						}
					} else {
						var o = _2bf.file;
						t.push('--' + this.multipartBoundary, 'Content-Disposition: form-data; name="'
							+ o.name
							+ '"; filename="'
							+ ('fileName' in o ? o.fileName : o.name)
							+ '"', 'Content-Type: ' + ('contentType'
						in o ? o.contentType : 'application/octet-stream'), '', o.content)
					}
				}
				if (t.length) {
					t.push('--' + this.multipartBoundary + '--', '');
					_2c1 = t.join('\r\n')
				}
			} while (false);
			var _2ca = _2bf['sync'] ? false : true;
			var _2cb = _2bf['preventCache'] || (this.preventCache == true && _2bf['preventCache'] != false);
			var _2cc = _2bf['useCache'] == true || (this.useCache == true && _2bf['useCache'] != false);
			if (!_2cb && _2cc) {
				var _2cd = getFromCache(url, _2c1, _2bf.method);
				if (_2cd) {
					doLoad(_2bf, _2cd, url, _2c1, false);
					return
				}
			}
			var http = dojo.hostenv.getXmlhttpObject(_2bf);
			var _2cf = false;
			if (_2ca) {
				var _2d0 = this.inFlight.push({
					'req': _2bf,
					'http': http,
					'url': url,
					'query': _2c1,
					'useCache': _2cc,
					'startTime': _2bf.timeoutSeconds ? (new Date()).getTime() : 0
				});
				this.startWatchingInFlight()
			} else {
				_29e._blockAsync = true
			}
			if (_2bf.method.toLowerCase() == 'post') {
				if (!_2bf.user) {
					http.open('POST', url, _2ca)
				} else {
					http.open('POST', url, _2ca, _2bf.user, _2bf.password)
				}
				setHeaders(http, _2bf);
				http.setRequestHeader('Content-Type', _2bf.multipart ? ('multipart/form-data; boundary='
					+ this.multipartBoundary) : (_2bf.contentType || 'application/x-www-form-urlencoded'));
				try {
					http.send(_2c1)
				} catch (e) {
					if (typeof http.abort == 'function') {
						http.abort()
					}
					doLoad(_2bf, {status: 404}, url, _2c1, _2cc)
				}
			} else {
				var _2d1 = url;
				if (_2c1 != '') {
					_2d1 += (_2d1.indexOf('?') > -1 ? '&' : '?') + _2c1
				}
				if (_2cb) {
					_2d1 += (dojo.string.endsWithAny(_2d1, '?', '&') ? '' : (_2d1.indexOf('?') > -1 ? '&' : '?'))
						+ 'dojo.preventCache='
						+ new Date().valueOf()
				}
				if (!_2bf.user) {
					http.open(_2bf.method.toUpperCase(), _2d1, _2ca)
				} else {
					http.open(_2bf.method.toUpperCase(), _2d1, _2ca, _2bf.user, _2bf.password)
				}
				setHeaders(http, _2bf);
				try {
					http.send(null)
				} catch (e) {
					if (typeof http.abort == 'function') {
						http.abort()
					}
					doLoad(_2bf, {status: 404}, url, _2c1, _2cc)
				}
			}
			if (!_2ca) {
				doLoad(_2bf, http, url, _2c1, _2cc);
				_29e._blockAsync = false
			}
			_2bf.abort = function () {
				try {
					http._aborted = true
				} catch (e) {
				}
				return http.abort()
			};

		};
		dojo.io.transports.addTransport('XMLHTTPTransport')
	};
}
dojo.provide('dojo.io.cookie');
dojo.io.cookie.setCookie = function (name, _2d3, days, path, _2d6, _2d7) {
	var _2d8 = -1;
	if ((typeof days == 'number') && (days >= 0)) {
		var d = new Date();
		d.setTime(d.getTime() + (days * 24 * 60 * 60 * 1000));
		_2d8 = d.toGMTString()
	}
	_2d3 = escape(_2d3);
	document.cookie = name + '=' + _2d3 + ';' + (_2d8 != -1 ? ' expires=' + _2d8 + ';' : '') + (path ? 'path='
		+ path : '') + (_2d6 ? '; domain=' + _2d6 : '') + (_2d7 ? '; secure' : '')
};
dojo.io.cookie.set = dojo.io.cookie.setCookie;
dojo.io.cookie.getCookie = function (name) {
	var idx = document.cookie.lastIndexOf(name + '=');
	if (idx == -1) {
		return null
	}
	var _2dc = document.cookie.substring(idx + name.length + 1);
	var end = _2dc.indexOf(';');
	if (end == -1) {
		end = _2dc.length
	}
	_2dc = _2dc.substring(0, end);
	_2dc = unescape(_2dc);
	return _2dc
};
dojo.io.cookie.get = dojo.io.cookie.getCookie;
dojo.io.cookie.deleteCookie = function (name) {
	dojo.io.cookie.setCookie(name, '-', 0)
};
dojo.io.cookie.setObjectCookie = function (name, obj, days, path, _2e3, _2e4, _2e5) {
	if (arguments.length == 5) {
		_2e5 = _2e3;
		_2e3 = null;
		_2e4 = null
	}
	var _2e6 = [], _2e7, _2e8 = '';
	if (!_2e5) {
		_2e7 = dojo.io.cookie.getObjectCookie(name)
	}
	if (days >= 0) {
		if (!_2e7) {
			_2e7 = {}
		}
		for (var prop in obj) {
			if (obj[prop] == null) {
				delete _2e7[prop]
			} else {
				if ((typeof obj[prop] == 'string') || (typeof obj[prop] == 'number')) {
					_2e7[prop] = obj[prop]
				}
			}
		}
		prop = null;
		for (var prop in _2e7) {
			_2e6.push(escape(prop) + '=' + escape(_2e7[prop]))
		}
		_2e8 = _2e6.join('&')
	}
	dojo.io.cookie.setCookie(name, _2e8, days, path, _2e3, _2e4)
};
dojo.io.cookie.getObjectCookie = function (name) {
	var _2eb = null, _2ec = dojo.io.cookie.getCookie(name);
	if (_2ec) {
		_2eb = {};
		var _2ed = _2ec.split('&');
		for (var i = 0; i < _2ed.length; i++) {
			var pair = _2ed[i].split('=');
			var _2f0 = pair[1];
			if (isNaN(_2f0)) {
				_2f0 = unescape(pair[1])
			}
			_2eb[unescape(pair[0])] = _2f0
		}
	}
	return _2eb
};
dojo.io.cookie.isSupported = function () {
	if (typeof navigator.cookieEnabled != 'boolean') {
		dojo.io.cookie.setCookie('__TestingYourBrowserForCookieSupport__', 'CookiesAllowed', 90, null);
		var _2f1 = dojo.io.cookie.getCookie('__TestingYourBrowserForCookieSupport__');
		navigator.cookieEnabled = (_2f1 == 'CookiesAllowed');
		if (navigator.cookieEnabled) {
			this.deleteCookie('__TestingYourBrowserForCookieSupport__')
		}
	}
	return navigator.cookieEnabled
};
if (!dojo.io.cookies) {
	dojo.io.cookies = dojo.io.cookie
}
dojo.kwCompoundRequire({
	common: ['dojo.io.common'],
	rhino: ['dojo.io.RhinoIO'],
	browser: ['dojo.io.BrowserIO', 'dojo.io.cookie'],
	dashboard: ['dojo.io.BrowserIO', 'dojo.io.cookie']
});
dojo.provide('dojo.io.*');
dojo.provide('dojo.event.common');
dojo.event = new function () {
	this._canTimeout = dojo.lang.isFunction(dj_global['setTimeout']) || dojo.lang.isAlien(dj_global['setTimeout']);

	function interpolateArgs(args, _2f3) {
		var dl = dojo.lang;
		var ao = {
			srcObj: dj_global,
			srcFunc: null,
			adviceObj: dj_global,
			adviceFunc: null,
			aroundObj: null,
			aroundFunc: null,
			adviceType: (args.length > 2) ? args[0] : 'after',
			precedence: 'last',
			once: false,
			delay: null,
			rate: 0,
			adviceMsg: false,
			maxCalls: -1
		};
		switch (args.length) {
			case 0:
				return;
			case 1:
				return;
			case 2:
				ao.srcFunc = args[0];
				ao.adviceFunc = args[1];
				break;
			case 3:
				if ((dl.isObject(args[0])) && (dl.isString(args[1])) && (dl.isString(args[2]))) {
					ao.adviceType = 'after';
					ao.srcObj = args[0];
					ao.srcFunc = args[1];
					ao.adviceFunc = args[2]
				} else {
					if ((dl.isString(args[1])) && (dl.isString(args[2]))) {
						ao.srcFunc = args[1];
						ao.adviceFunc = args[2]
					} else {
						if ((dl.isObject(args[0])) && (dl.isString(args[1])) && (dl.isFunction(args[2]))) {
							ao.adviceType = 'after';
							ao.srcObj = args[0];
							ao.srcFunc = args[1];
							var _2f6 = dl.nameAnonFunc(args[2], ao.adviceObj, _2f3);
							ao.adviceFunc = _2f6
						} else {
							if ((dl.isFunction(args[0])) && (dl.isObject(args[1])) && (dl.isString(args[2]))) {
								ao.adviceType = 'after';
								ao.srcObj = dj_global;
								var _2f6 = dl.nameAnonFunc(args[0], ao.srcObj, _2f3);
								ao.srcFunc = _2f6;
								ao.adviceObj = args[1];
								ao.adviceFunc = args[2]
							}
						}
					}
				}
				break;
			case 4:
				if ((dl.isObject(args[0])) && (dl.isObject(args[2]))) {
					ao.adviceType = 'after';
					ao.srcObj = args[0];
					ao.srcFunc = args[1];
					ao.adviceObj = args[2];
					ao.adviceFunc = args[3]
				} else {
					if ((dl.isString(args[0])) && (dl.isString(args[1])) && (dl.isObject(args[2]))) {
						ao.adviceType = args[0];
						ao.srcObj = dj_global;
						ao.srcFunc = args[1];
						ao.adviceObj = args[2];
						ao.adviceFunc = args[3]
					} else {
						if ((dl.isString(args[0])) && (dl.isFunction(args[1])) && (dl.isObject(args[2]))) {
							ao.adviceType = args[0];
							ao.srcObj = dj_global;
							var _2f6 = dl.nameAnonFunc(args[1], dj_global, _2f3);
							ao.srcFunc = _2f6;
							ao.adviceObj = args[2];
							ao.adviceFunc = args[3]
						} else {
							if ((dl.isString(args[0]))
								&& (dl.isObject(args[1]))
								&& (dl.isString(args[2]))
								&& (dl.isFunction(args[3]))) {
								ao.srcObj = args[1];
								ao.srcFunc = args[2];
								var _2f6 = dl.nameAnonFunc(args[3], dj_global, _2f3);
								ao.adviceObj = dj_global;
								ao.adviceFunc = _2f6
							} else {
								if (dl.isObject(args[1])) {
									ao.srcObj = args[1];
									ao.srcFunc = args[2];
									ao.adviceObj = dj_global;
									ao.adviceFunc = args[3]
								} else {
									if (dl.isObject(args[2])) {
										ao.srcObj = dj_global;
										ao.srcFunc = args[1];
										ao.adviceObj = args[2];
										ao.adviceFunc = args[3]
									} else {
										ao.srcObj = ao.adviceObj = ao.aroundObj = dj_global;
										ao.srcFunc = args[1];
										ao.adviceFunc = args[2];
										ao.aroundFunc = args[3]
									}
								}
							}
						}
					}
				}
				break;
			case 6:
				ao.srcObj = args[1];
				ao.srcFunc = args[2];
				ao.adviceObj = args[3];
				ao.adviceFunc = args[4];
				ao.aroundFunc = args[5];
				ao.aroundObj = dj_global;
				break;
			default:
				ao.srcObj = args[1];
				ao.srcFunc = args[2];
				ao.adviceObj = args[3];
				ao.adviceFunc = args[4];
				ao.aroundObj = args[5];
				ao.aroundFunc = args[6];
				ao.once = args[7];
				ao.delay = args[8];
				ao.rate = args[9];
				ao.adviceMsg = args[10];
				ao.maxCalls = (!isNaN(parseInt(args[11]))) ? args[11] : -1;
				break
		}
		if (dl.isFunction(ao.aroundFunc)) {
			var _2f6 = dl.nameAnonFunc(ao.aroundFunc, ao.aroundObj, _2f3);
			ao.aroundFunc = _2f6
		}
		if (dl.isFunction(ao.srcFunc)) {
			ao.srcFunc = dl.getNameInObj(ao.srcObj, ao.srcFunc)
		}
		if (dl.isFunction(ao.adviceFunc)) {
			ao.adviceFunc = dl.getNameInObj(ao.adviceObj, ao.adviceFunc)
		}
		if ((ao.aroundObj) && (dl.isFunction(ao.aroundFunc))) {
			ao.aroundFunc = dl.getNameInObj(ao.aroundObj, ao.aroundFunc)
		}
		if (!ao.srcObj) {
			dojo.raise('bad srcObj for srcFunc: ' + ao.srcFunc)
		}
		if (!ao.adviceObj) {
			dojo.raise('bad adviceObj for adviceFunc: ' + ao.adviceFunc)
		}
		if (!ao.adviceFunc) {
			dojo.debug('bad adviceFunc for srcFunc: ' + ao.srcFunc);
			dojo.debugShallow(ao)
		}
		return ao
	}

	this.connect = function () {
		if (arguments.length == 1) {
			var ao = arguments[0]
		} else {
			var ao = interpolateArgs(arguments, true)
		}
		if (dojo.lang.isString(ao.srcFunc) && (ao.srcFunc.toLowerCase() == 'onkey')) {
			if (dojo.render.html.ie) {
				ao.srcFunc = 'onkeydown';
				this.connect(ao)
			}
			ao.srcFunc = 'onkeypress'
		}
		if (dojo.lang.isArray(ao.srcObj) && ao.srcObj != '') {
			var _2f8 = {};
			for (var x in ao) {
				_2f8[x] = ao[x]
			}
			var mjps = [];
			dojo.lang.forEach(ao.srcObj, function (src) {
				if ((dojo.render.html.capable) && (dojo.lang.isString(src))) {
					src = dojo.byId(src)
				}
				_2f8.srcObj = src;
				mjps.push(dojo.event.connect.call(dojo.event, _2f8))
			});
			return mjps
		}
		var mjp = dojo.event.MethodJoinPoint.getForMethod(ao.srcObj, ao.srcFunc);
		if (ao.adviceFunc) {
			var mjp2 = dojo.event.MethodJoinPoint.getForMethod(ao.adviceObj, ao.adviceFunc)
		}
		mjp.kwAddAdvice(ao);
		return mjp
	};
	this.log = function (a1, a2) {
		var _300;
		if ((arguments.length == 1) && (typeof a1 == 'object')) {
			_300 = a1
		} else {
			_300 = {srcObj: a1, srcFunc: a2}
		}
		_300.adviceFunc = function () {
			var _301 = [];
			for (var x = 0; x < arguments.length; x++) {
				_301.push(arguments[x])
			}
			dojo.debug('(' + _300.srcObj + ').' + _300.srcFunc, ':', _301.join(', '))
		};
		this.kwConnect(_300)
	};
	this.connectBefore = function () {
		var args = ['before'];
		for (var i = 0; i < arguments.length; i++) {
			args.push(arguments[i])
		}
		return this.connect.apply(this, args)
	};
	this.connectAround = function () {
		var args = ['around'];
		for (var i = 0; i < arguments.length; i++) {
			args.push(arguments[i])
		}
		return this.connect.apply(this, args)
	};
	this.connectOnce = function () {
		var ao = interpolateArgs(arguments, true);
		ao.once = true;
		return this.connect(ao)
	};
	this.connectRunOnce = function () {
		var ao = interpolateArgs(arguments, true);
		ao.maxCalls = 1;
		return this.connect(ao)
	};
	this._kwConnectImpl = function (_309, _30a) {
		var fn = (_30a) ? 'disconnect' : 'connect';
		if (typeof _309['srcFunc'] == 'function') {
			_309.srcObj = _309['srcObj'] || dj_global;
			var _30c = dojo.lang.nameAnonFunc(_309.srcFunc, _309.srcObj, true);
			_309.srcFunc = _30c
		}
		if (typeof _309['adviceFunc'] == 'function') {
			_309.adviceObj = _309['adviceObj'] || dj_global;
			var _30c = dojo.lang.nameAnonFunc(_309.adviceFunc, _309.adviceObj, true);
			_309.adviceFunc = _30c
		}
		_309.srcObj = _309['srcObj'] || dj_global;
		_309.adviceObj = _309['adviceObj'] || _309['targetObj'] || dj_global;
		_309.adviceFunc = _309['adviceFunc'] || _309['targetFunc'];
		return dojo.event[fn](_309)
	};
	this.kwConnect = function (_30d) {
		return this._kwConnectImpl(_30d, false)
	};
	this.disconnect = function () {
		if (arguments.length == 1) {
			var ao = arguments[0]
		} else {
			var ao = interpolateArgs(arguments, true)
		}
		if (!ao.adviceFunc) {
			return
		}
		if (dojo.lang.isString(ao.srcFunc) && (ao.srcFunc.toLowerCase() == 'onkey')) {
			if (dojo.render.html.ie) {
				ao.srcFunc = 'onkeydown';
				this.disconnect(ao)
			}
			ao.srcFunc = 'onkeypress'
		}
		if (!ao.srcObj[ao.srcFunc]) {
			return null
		}
		var mjp = dojo.event.MethodJoinPoint.getForMethod(ao.srcObj, ao.srcFunc, true);
		mjp.removeAdvice(ao.adviceObj, ao.adviceFunc, ao.adviceType, ao.once);
		return mjp
	};
	this.kwDisconnect = function (_310) {
		return this._kwConnectImpl(_310, true)
	}
};
;dojo.event.MethodInvocation = function (_311, obj, args) {
	this.jp_ = _311;
	this.object = obj;
	this.args = [];
	for (var x = 0; x < args.length; x++) {
		this.args[x] = args[x]
	}
	this.around_index = -1
};
dojo.event.MethodInvocation.prototype.proceed = function () {
	this.around_index++;
	if (this.around_index >= this.jp_.around.length) {
		return this.jp_.object[this.jp_.methodname].apply(this.jp_.object, this.args)
	} else {
		var ti = this.jp_.around[this.around_index];
		var mobj = ti[0] || dj_global;
		var meth = ti[1];
		return mobj[meth].call(mobj, this)
	}
};
dojo.event.MethodJoinPoint = function (obj, _319) {
	this.object = obj || dj_global;
	this.methodname = _319;
	this.methodfunc = this.object[_319];
	this.squelch = false
};
dojo.event.MethodJoinPoint.getForMethod = function (obj, _31b) {
	if (!obj) {
		obj = dj_global
	}
	var ofn = obj[_31b];
	if (!ofn) {
		ofn = obj[_31b] = function () {
		};
		if (!obj[_31b]) {
			dojo.raise('Cannot set do-nothing method on that object ' + _31b)
		}
	} else {
		if ((typeof ofn != 'function') && (!dojo.lang.isFunction(ofn)) && (!dojo.lang.isAlien(ofn))) {
			return null
		}
	}
	var _31d = _31b + '$joinpoint';
	var _31e = _31b + '$joinpoint$method';
	var _31f = obj[_31d];
	if (!_31f) {
		var _320 = false;
		if (dojo.event['browser']) {
			if ((obj['attachEvent']) || (obj['nodeType']) || (obj['addEventListener'])) {
				_320 = true;
				dojo.event.browser.addClobberNodeAttrs(obj, [_31d, _31e, _31b])
			}
		}
		var _321 = ofn.length;
		obj[_31e] = ofn;
		_31f = obj[_31d] = new dojo.event.MethodJoinPoint(obj, _31e);
		if (!_320) {
			obj[_31b] = function () {
				return _31f.run.apply(_31f, arguments)
			}
		} else {
			obj[_31b] = function () {
				var args = [];
				if (!arguments.length) {
					var evt = null;
					try {
						if (obj.ownerDocument) {
							evt = obj.ownerDocument.parentWindow.event
						} else {
							if (obj.documentElement) {
								evt = obj.documentElement.ownerDocument.parentWindow.event
							} else {
								if (obj.event) {
									evt = obj.event
								} else {
									evt = window.event
								}
							}
						}
					} catch (e) {
						evt = window.event
					}
					if (evt) {
						args.push(dojo.event.browser.fixEvent(evt, this))
					}
				} else {
					for (var x = 0; x < arguments.length; x++) {
						if ((x == 0) && (dojo.event.browser.isEvent(arguments[x]))) {
							args.push(dojo.event.browser.fixEvent(arguments[x], this))
						} else {
							args.push(arguments[x])
						}
					}
				}
				return _31f.run.apply(_31f, args)
			}
		}
		obj[_31b].__preJoinArity = _321
	}
	return _31f
};
dojo.lang.extend(dojo.event.MethodJoinPoint, {
	squelch: false, unintercept: function () {
		this.object[this.methodname] = this.methodfunc;
		this.before = [];
		this.after = [];
		this.around = []
	}, disconnect: dojo.lang.forward('unintercept'), run: function () {
		var obj = this.object || dj_global;
		var args = arguments;
		var _327 = [];
		for (var x = 0; x < args.length; x++) {
			_327[x] = args[x]
		}
		var _329 = function (marr) {
			if (!marr) {
				dojo.debug('Null argument to unrollAdvice()');
				return
			}
			var _32b = marr[0] || dj_global;
			var _32c = marr[1];
			if (!_32b[_32c]) {
				dojo.raise('function "' + _32c + '" does not exist on "' + _32b + '"')
			}
			var _32d = marr[2] || dj_global;
			var _32e = marr[3];
			var msg = marr[6];
			var _330 = marr[7];
			if (_330 > -1) {
				if (_330 == 0) {
					return
				}
				marr[7]--
			}
			var _331;
			var to = {
				args: [], jp_: this, object: obj, proceed: function () {
					return _32b[_32c].apply(_32b, to.args)
				}
			};
			to.args = _327;
			var _333 = parseInt(marr[4]);
			var _334 = ((!isNaN(_333)) && (marr[4] !== null) && (typeof marr[4] != 'undefined'));
			if (marr[5]) {
				var rate = parseInt(marr[5]);
				var cur = new Date();
				var _337 = false;
				if ((marr['last']) && ((cur - marr.last) <= rate)) {
					if (dojo.event._canTimeout) {
						if (marr['delayTimer']) {
							clearTimeout(marr.delayTimer)
						}
						var tod = parseInt(rate * 2);
						var mcpy = dojo.lang.shallowCopy(marr);
						marr.delayTimer = setTimeout(function () {
							mcpy[5] = 0;
							_329(mcpy)
						}, tod)
					}
					return
				} else {
					marr.last = cur
				}
			}
			if (_32e) {
				_32d[_32e].call(_32d, to)
			} else {
				if ((_334) && ((dojo.render.html) || (dojo.render.svg))) {
					dj_global['setTimeout'](function () {
						if (msg) {
							_32b[_32c].call(_32b, to)
						} else {
							_32b[_32c].apply(_32b, args)
						}
					}, _333)
				} else {
					if (msg) {
						_32b[_32c].call(_32b, to)
					} else {
						_32b[_32c].apply(_32b, args)
					}
				}
			}
		};
		var _33a = function () {
			if (this.squelch) {
				try {
					return _329.apply(this, arguments)
				} catch (e) {
					dojo.debug(e)
				}
			} else {
				return _329.apply(this, arguments)
			}
		};
		if ((this['before']) && (this.before.length > 0)) {
			dojo.lang.forEach(this.before.concat([]), _33a)
		}
		var _33b;
		try {
			if ((this['around']) && (this.around.length > 0)) {
				var mi = new dojo.event.MethodInvocation(this, obj, args);
				_33b = mi.proceed()
			} else {
				if (this.methodfunc) {
					_33b = this.object[this.methodname].apply(this.object, args)
				}
			}
		} catch (e) {
			if (!this.squelch) {
				dojo.debug(e, 'when calling', this.methodname, 'on', this.object, 'with arguments', args);
				dojo.raise(e)
			}
		}
		if ((this['after']) && (this.after.length > 0)) {
			dojo.lang.forEach(this.after.concat([]), _33a)
		}
		return (this.methodfunc) ? _33b : null
	}, getArr: function (kind) {
		var type = 'after';
		if ((typeof kind == 'string') && (kind.indexOf('before') != -1)) {
			type = 'before'
		} else {
			if (kind == 'around') {
				type = 'around'
			}
		}
		if (!this[type]) {
			this[type] = []
		}
		return this[type]
	}, kwAddAdvice: function (args) {
		this.addAdvice(args['adviceObj'], args['adviceFunc'], args['aroundObj'], args['aroundFunc'], args['adviceType'], args['precedence'], args['once'], args['delay'], args['rate'], args['adviceMsg'], args['maxCalls'])
	}, addAdvice: function (_340, _341, _342, _343, _344, _345, once, _347, rate, _349, _34a) {
		var arr = this.getArr(_344);
		if (!arr) {
			dojo.raise('bad this: ' + this)
		}
		var ao = [_340, _341, _342, _343, _347, rate, _349, _34a];
		if (once) {
			if (this.hasAdvice(_340, _341, _344, arr) >= 0) {
				return
			}
		}
		if (_345 == 'first') {
			arr.unshift(ao)
		} else {
			arr.push(ao)
		}
	}, hasAdvice: function (_34d, _34e, _34f, arr) {
		if (!arr) {
			arr = this.getArr(_34f)
		}
		var ind = -1;
		for (var x = 0; x < arr.length; x++) {
			var aao = (typeof _34e == 'object') ? (String(_34e)).toString() : _34e;
			var a1o = (typeof arr[x][1] == 'object') ? (String(arr[x][1])).toString() : arr[x][1];
			if ((arr[x][0] == _34d) && (a1o == aao)) {
				ind = x
			}
		}
		return ind
	}, removeAdvice: function (_355, _356, _357, once) {
		var arr = this.getArr(_357);
		var ind = this.hasAdvice(_355, _356, _357, arr);
		if (ind == -1) {
			return false
		}
		while (ind != -1) {
			arr.splice(ind, 1);
			if (once) {
				break
			}
			ind = this.hasAdvice(_355, _356, _357, arr)
		}
		return true
	}
});
dojo.provide('dojo.event.topic');
dojo.event.topic = new function () {
	this.topics = {};
	this.getTopic = function (_35b) {
		if (!this.topics[_35b]) {
			this.topics[_35b] = new this.TopicImpl(_35b)
		}
		return this.topics[_35b]
	};
	this.registerPublisher = function (_35c, obj, _35e) {
		var _35c = this.getTopic(_35c);
		_35c.registerPublisher(obj, _35e)
	};
	this.subscribe = function (_35f, obj, _361) {
		var _35f = this.getTopic(_35f);
		_35f.subscribe(obj, _361)
	};
	this.unsubscribe = function (_362, obj, _364) {
		var _362 = this.getTopic(_362);
		_362.unsubscribe(obj, _364)
	};
	this.destroy = function (_365) {
		this.getTopic(_365).destroy();
		delete this.topics[_365]
	};
	this.publishApply = function (_366, args) {
		var _366 = this.getTopic(_366);
		_366.sendMessage.apply(_366, args)
	};
	this.publish = function (_368, _369) {
		var _368 = this.getTopic(_368);
		var args = [];
		for (var x = 1; x < arguments.length; x++) {
			args.push(arguments[x])
		}
		_368.sendMessage.apply(_368, args)
	}
};
;dojo.event.topic.TopicImpl = function (_36c) {
	this.topicName = _36c;
	this.subscribe = function (_36d, _36e) {
		var tf = _36e || _36d;
		var to = (!_36e) ? dj_global : _36d;
		return dojo.event.kwConnect({srcObj: this, srcFunc: 'sendMessage', adviceObj: to, adviceFunc: tf})
	};
	this.unsubscribe = function (_371, _372) {
		var tf = (!_372) ? _371 : _372;
		var to = (!_372) ? null : _371;
		return dojo.event.kwDisconnect({srcObj: this, srcFunc: 'sendMessage', adviceObj: to, adviceFunc: tf})
	};
	this._getJoinPoint = function () {
		return dojo.event.MethodJoinPoint.getForMethod(this, 'sendMessage')
	};
	this.setSquelch = function (_375) {
		this._getJoinPoint().squelch = _375
	};
	this.destroy = function () {
		this._getJoinPoint().disconnect()
	};
	this.registerPublisher = function (_376, _377) {
		dojo.event.connect(_376, _377, this, 'sendMessage')
	};
	this.sendMessage = function (_378) {
	}
};
dojo.provide('dojo.event.browser');
dojo._ie_clobber = new function () {
	this.clobberNodes = [];

	function nukeProp(node, prop) {
		try {
			node[prop] = null
		} catch (e) {
		}
		try {
			delete node[prop]
		} catch (e) {
		}
		try {
			node.removeAttribute(prop)
		} catch (e) {
		}
	}

	this.clobber = function (_37b) {
		var na;
		var tna;
		if (_37b) {
			tna = _37b.all || _37b.getElementsByTagName('*');
			na = [_37b];
			for (var x = 0; x < tna.length; x++) {
				if (tna[x]['__doClobber__']) {
					na.push(tna[x])
				}
			}
		} else {
			try {
				window.onload = null
			} catch (e) {
			}
			na = (this.clobberNodes.length) ? this.clobberNodes : document.all
		}
		tna = null;
		var _37f = {};
		for (var i = na.length - 1; i >= 0; i = i - 1) {
			var el = na[i];
			try {
				if (el && el['__clobberAttrs__']) {
					for (var j = 0; j < el.__clobberAttrs__.length; j++) {
						nukeProp(el, el.__clobberAttrs__[j])
					}
					nukeProp(el, '__clobberAttrs__');
					nukeProp(el, '__doClobber__')
				}
			} catch (e) {
			}
		}
		na = null
	}
};
;
if (dojo.render.html.ie) {
	dojo.addOnUnload(function () {
		dojo._ie_clobber.clobber();
		try {
			if ((dojo['widget']) && (dojo.widget['manager'])) {
				dojo.widget.manager.destroyAll()
			}
		} catch (e) {
		}
		if (dojo.widget) {
			for (var name in dojo.widget._templateCache) {
				if (dojo.widget._templateCache[name].node) {
					dojo.dom.destroyNode(dojo.widget._templateCache[name].node);
					dojo.widget._templateCache[name].node = null;
					delete dojo.widget._templateCache[name].node
				}
			}
		}
		try {
			window.onload = null
		} catch (e) {
		}
		try {
			window.onunload = null
		} catch (e) {
		}
		dojo._ie_clobber.clobberNodes = []
	})
}
dojo.event.browser = new function () {
	var _384 = 0;
	this.normalizedEventName = function (_385) {
		switch (_385) {
			case 'CheckboxStateChange':
			case 'DOMAttrModified':
			case 'DOMMenuItemActive':
			case 'DOMMenuItemInactive':
			case 'DOMMouseScroll':
			case 'DOMNodeInserted':
			case 'DOMNodeRemoved':
			case 'RadioStateChange':
				return _385;
				break;
			default:
				var lcn = _385.toLowerCase();
				return (lcn.indexOf('on') == 0) ? lcn.substr(2) : lcn;
				break
		}
	};
	this.clean = function (node) {
		if (dojo.render.html.ie) {
			dojo._ie_clobber.clobber(node)
		}
	};
	this.addClobberNode = function (node) {
		if (!dojo.render.html.ie) {
			return
		}
		if (!node['__doClobber__']) {
			node.__doClobber__ = true;
			dojo._ie_clobber.clobberNodes.push(node);
			node.__clobberAttrs__ = []
		}
	};
	this.addClobberNodeAttrs = function (node, _38a) {
		if (!dojo.render.html.ie) {
			return
		}
		this.addClobberNode(node);
		for (var x = 0; x < _38a.length; x++) {
			node.__clobberAttrs__.push(_38a[x])
		}
	};
	this.removeListener = function (node, _38d, fp, _38f) {
		if (!_38f) {
			var _38f = false
		}
		_38d = dojo.event.browser.normalizedEventName(_38d);
		if (_38d == 'key') {
			if (dojo.render.html.ie) {
				this.removeListener(node, 'onkeydown', fp, _38f)
			}
			_38d = 'keypress'
		}
		if (node.removeEventListener) {
			node.removeEventListener(_38d, fp, _38f)
		}
	};
	this.addListener = function (node, _391, fp, _393, _394) {
		if (!node) {
			return
		}
		if (!_393) {
			var _393 = false
		}
		_391 = dojo.event.browser.normalizedEventName(_391);
		if (_391 == 'key') {
			if (dojo.render.html.ie) {
				this.addListener(node, 'onkeydown', fp, _393, _394)
			}
			_391 = 'keypress'
		}
		if (!_394) {
			var _395 = function (evt) {
				if (!evt) {
					evt = window.event
				}
				var ret = fp(dojo.event.browser.fixEvent(evt, this));
				if (_393) {
					dojo.event.browser.stopEvent(evt)
				}
				return ret
			}
		} else {
			_395 = fp
		}
		if (node.addEventListener) {
			node.addEventListener(_391, _395, _393);
			return _395
		} else {
			_391 = 'on' + _391;
			if (typeof node[_391] == 'function') {
				var _398 = node[_391];
				node[_391] = function (e) {
					_398(e);
					return _395(e)
				}
			} else {
				node[_391] = _395
			}
			if (dojo.render.html.ie) {
				this.addClobberNodeAttrs(node, [_391])
			}
			return _395
		}
	};
	this.isEvent = function (obj) {
		return (typeof obj != 'undefined') && (obj) && (typeof Event != 'undefined') && (obj.eventPhase)
	};
	this.currentEvent = null;
	this.callListener = function (_39b, _39c) {
		if (typeof _39b != 'function') {
			dojo.raise('listener not a function: ' + _39b)
		}
		dojo.event.browser.currentEvent.currentTarget = _39c;
		return _39b.call(_39c, dojo.event.browser.currentEvent)
	};
	this._stopPropagation = function () {
		dojo.event.browser.currentEvent.cancelBubble = true
	};
	this._preventDefault = function () {
		dojo.event.browser.currentEvent.returnValue = false
	};
	this.keys = {
		KEY_BACKSPACE: 8,
		KEY_TAB: 9,
		KEY_CLEAR: 12,
		KEY_ENTER: 13,
		KEY_SHIFT: 16,
		KEY_CTRL: 17,
		KEY_ALT: 18,
		KEY_PAUSE: 19,
		KEY_CAPS_LOCK: 20,
		KEY_ESCAPE: 27,
		KEY_SPACE: 32,
		KEY_PAGE_UP: 33,
		KEY_PAGE_DOWN: 34,
		KEY_END: 35,
		KEY_HOME: 36,
		KEY_LEFT_ARROW: 37,
		KEY_UP_ARROW: 38,
		KEY_RIGHT_ARROW: 39,
		KEY_DOWN_ARROW: 40,
		KEY_INSERT: 45,
		KEY_DELETE: 46,
		KEY_HELP: 47,
		KEY_LEFT_WINDOW: 91,
		KEY_RIGHT_WINDOW: 92,
		KEY_SELECT: 93,
		KEY_NUMPAD_0: 96,
		KEY_NUMPAD_1: 97,
		KEY_NUMPAD_2: 98,
		KEY_NUMPAD_3: 99,
		KEY_NUMPAD_4: 100,
		KEY_NUMPAD_5: 101,
		KEY_NUMPAD_6: 102,
		KEY_NUMPAD_7: 103,
		KEY_NUMPAD_8: 104,
		KEY_NUMPAD_9: 105,
		KEY_NUMPAD_MULTIPLY: 106,
		KEY_NUMPAD_PLUS: 107,
		KEY_NUMPAD_ENTER: 108,
		KEY_NUMPAD_MINUS: 109,
		KEY_NUMPAD_PERIOD: 110,
		KEY_NUMPAD_DIVIDE: 111,
		KEY_F1: 112,
		KEY_F2: 113,
		KEY_F3: 114,
		KEY_F4: 115,
		KEY_F5: 116,
		KEY_F6: 117,
		KEY_F7: 118,
		KEY_F8: 119,
		KEY_F9: 120,
		KEY_F10: 121,
		KEY_F11: 122,
		KEY_F12: 123,
		KEY_F13: 124,
		KEY_F14: 125,
		KEY_F15: 126,
		KEY_NUM_LOCK: 144,
		KEY_SCROLL_LOCK: 145
	};
	this.revKeys = [];
	for (var key in this.keys) {
		this.revKeys[this.keys[key]] = key
	}
	this.fixEvent = function (evt, _39f) {
		if (!evt) {
			if (window['event']) {
				evt = window.event
			}
		}
		if ((evt['type']) && (evt['type'].indexOf('key') == 0)) {
			evt.keys = this.revKeys;
			for (var key in this.keys) {
				evt[key] = this.keys[key]
			}
			if (evt['type'] == 'keydown' && dojo.render.html.ie) {
				switch (evt.keyCode) {
					case evt.KEY_SHIFT:
					case evt.KEY_CTRL:
					case evt.KEY_ALT:
					case evt.KEY_CAPS_LOCK:
					case evt.KEY_LEFT_WINDOW:
					case evt.KEY_RIGHT_WINDOW:
					case evt.KEY_SELECT:
					case evt.KEY_NUM_LOCK:
					case evt.KEY_SCROLL_LOCK:
					case evt.KEY_NUMPAD_0:
					case evt.KEY_NUMPAD_1:
					case evt.KEY_NUMPAD_2:
					case evt.KEY_NUMPAD_3:
					case evt.KEY_NUMPAD_4:
					case evt.KEY_NUMPAD_5:
					case evt.KEY_NUMPAD_6:
					case evt.KEY_NUMPAD_7:
					case evt.KEY_NUMPAD_8:
					case evt.KEY_NUMPAD_9:
					case evt.KEY_NUMPAD_PERIOD:
						break;
					case evt.KEY_NUMPAD_MULTIPLY:
					case evt.KEY_NUMPAD_PLUS:
					case evt.KEY_NUMPAD_ENTER:
					case evt.KEY_NUMPAD_MINUS:
					case evt.KEY_NUMPAD_DIVIDE:
						break;
					case evt.KEY_PAUSE:
					case evt.KEY_TAB:
					case evt.KEY_BACKSPACE:
					case evt.KEY_ENTER:
					case evt.KEY_ESCAPE:
					case evt.KEY_PAGE_UP:
					case evt.KEY_PAGE_DOWN:
					case evt.KEY_END:
					case evt.KEY_HOME:
					case evt.KEY_LEFT_ARROW:
					case evt.KEY_UP_ARROW:
					case evt.KEY_RIGHT_ARROW:
					case evt.KEY_DOWN_ARROW:
					case evt.KEY_INSERT:
					case evt.KEY_DELETE:
					case evt.KEY_F1:
					case evt.KEY_F2:
					case evt.KEY_F3:
					case evt.KEY_F4:
					case evt.KEY_F5:
					case evt.KEY_F6:
					case evt.KEY_F7:
					case evt.KEY_F8:
					case evt.KEY_F9:
					case evt.KEY_F10:
					case evt.KEY_F11:
					case evt.KEY_F12:
					case evt.KEY_F12:
					case evt.KEY_F13:
					case evt.KEY_F14:
					case evt.KEY_F15:
					case evt.KEY_CLEAR:
					case evt.KEY_HELP:
						evt.key = evt.keyCode;
						break;
					default:
						if (evt.ctrlKey || evt.altKey) {
							var _3a1 = evt.keyCode;
							if (_3a1 >= 65 && _3a1 <= 90 && evt.shiftKey == false) {
								_3a1 += 32
							}
							if (_3a1 >= 1 && _3a1 <= 26 && evt.ctrlKey) {
								_3a1 += 96
							}
							evt.key = String.fromCharCode(_3a1)
						}
				}
			} else {
				if (evt['type'] == 'keypress') {
					if (dojo.render.html.opera) {
						if (evt.which == 0) {
							evt.key = evt.keyCode
						} else {
							if (evt.which > 0) {
								switch (evt.which) {
									case evt.KEY_SHIFT:
									case evt.KEY_CTRL:
									case evt.KEY_ALT:
									case evt.KEY_CAPS_LOCK:
									case evt.KEY_NUM_LOCK:
									case evt.KEY_SCROLL_LOCK:
										break;
									case evt.KEY_PAUSE:
									case evt.KEY_TAB:
									case evt.KEY_BACKSPACE:
									case evt.KEY_ENTER:
									case evt.KEY_ESCAPE:
										evt.key = evt.which;
										break;
									default:
										var _3a1 = evt.which;
										if ((evt.ctrlKey || evt.altKey || evt.metaKey) && (evt.which
											>= 65
											&& evt.which
											<= 90
											&& evt.shiftKey
											== false)) {
											_3a1 += 32
										}
										evt.key = String.fromCharCode(_3a1)
								}
							}
						}
					} else {
						if (dojo.render.html.ie) {
							if (!evt.ctrlKey && !evt.altKey && evt.keyCode >= evt.KEY_SPACE) {
								evt.key = String.fromCharCode(evt.keyCode)
							}
						} else {
							if (dojo.render.html.safari) {
								switch (evt.keyCode) {
									case 25:
										evt.key = evt.KEY_TAB;
										evt.shift = true;
										break;
									case 63232:
										evt.key = evt.KEY_UP_ARROW;
										break;
									case 63233:
										evt.key = evt.KEY_DOWN_ARROW;
										break;
									case 63234:
										evt.key = evt.KEY_LEFT_ARROW;
										break;
									case 63235:
										evt.key = evt.KEY_RIGHT_ARROW;
										break;
									case 63236:
										evt.key = evt.KEY_F1;
										break;
									case 63237:
										evt.key = evt.KEY_F2;
										break;
									case 63238:
										evt.key = evt.KEY_F3;
										break;
									case 63239:
										evt.key = evt.KEY_F4;
										break;
									case 63240:
										evt.key = evt.KEY_F5;
										break;
									case 63241:
										evt.key = evt.KEY_F6;
										break;
									case 63242:
										evt.key = evt.KEY_F7;
										break;
									case 63243:
										evt.key = evt.KEY_F8;
										break;
									case 63244:
										evt.key = evt.KEY_F9;
										break;
									case 63245:
										evt.key = evt.KEY_F10;
										break;
									case 63246:
										evt.key = evt.KEY_F11;
										break;
									case 63247:
										evt.key = evt.KEY_F12;
										break;
									case 63250:
										evt.key = evt.KEY_PAUSE;
										break;
									case 63272:
										evt.key = evt.KEY_DELETE;
										break;
									case 63273:
										evt.key = evt.KEY_HOME;
										break;
									case 63275:
										evt.key = evt.KEY_END;
										break;
									case 63276:
										evt.key = evt.KEY_PAGE_UP;
										break;
									case 63277:
										evt.key = evt.KEY_PAGE_DOWN;
										break;
									case 63302:
										evt.key = evt.KEY_INSERT;
										break;
									case 63248:
									case 63249:
									case 63289:
										break;
									default:
										evt.key = evt.charCode >= evt.KEY_SPACE ? String.fromCharCode(evt.charCode) : evt.keyCode
								}
							} else {
								evt.key = evt.charCode > 0 ? String.fromCharCode(evt.charCode) : evt.keyCode
							}
						}
					}
				}
			}
		}
		if (dojo.render.html.ie) {
			if (!evt.target) {
				evt.target = evt.srcElement
			}
			if (!evt.currentTarget) {
				evt.currentTarget = (_39f ? _39f : evt.srcElement)
			}
			if (!evt.layerX) {
				evt.layerX = evt.offsetX
			}
			if (!evt.layerY) {
				evt.layerY = evt.offsetY
			}
			var doc = (evt.srcElement && evt.srcElement.ownerDocument) ? evt.srcElement.ownerDocument : document;
			var _3a3 = ((dojo.render.html.ie55) || (doc['compatMode'] == 'BackCompat')) ? doc.body : doc.documentElement;
			if (!evt.pageX) {
				evt.pageX = evt.clientX + (_3a3.scrollLeft || 0)
			}
			if (!evt.pageY) {
				evt.pageY = evt.clientY + (_3a3.scrollTop || 0)
			}
			if (evt.type == 'mouseover') {
				evt.relatedTarget = evt.fromElement
			}
			if (evt.type == 'mouseout') {
				evt.relatedTarget = evt.toElement
			}
			this.currentEvent = evt;
			evt.callListener = this.callListener;
			evt.stopPropagation = this._stopPropagation;
			evt.preventDefault = this._preventDefault
		}
		return evt
	};
	this.stopEvent = function (evt) {
		if (window.event) {
			evt.cancelBubble = true;
			evt.returnValue = false
		} else {
			evt.preventDefault();
			evt.stopPropagation()
		}
	}
};
;dojo.kwCompoundRequire({
	common: ['dojo.event.common', 'dojo.event.topic'], browser: ['dojo.event.browser'], dashboard: ['dojo.event.browser']
});
dojo.provide('dojo.event.*');
dojo.provide('dojo.gfx.color');
dojo.gfx.color.Color = function (r, g, b, a) {
	if (dojo.lang.isArray(r)) {
		this.r = r[0];
		this.g = r[1];
		this.b = r[2];
		this.a = r[3] || 1
	} else {
		if (dojo.lang.isString(r)) {
			var rgb = dojo.gfx.color.extractRGB(r);
			this.r = rgb[0];
			this.g = rgb[1];
			this.b = rgb[2];
			this.a = g || 1
		} else {
			if (r instanceof dojo.gfx.color.Color) {
				this.r = r.r;
				this.b = r.b;
				this.g = r.g;
				this.a = r.a
			} else {
				this.r = r;
				this.g = g;
				this.b = b;
				this.a = a
			}
		}
	}
};
dojo.gfx.color.Color.fromArray = function (arr) {
	return new dojo.gfx.color.Color(arr[0], arr[1], arr[2], arr[3])
};
dojo.extend(dojo.gfx.color.Color, {
	toRgb: function (_3ab) {
		if (_3ab) {
			return this.toRgba()
		} else {
			return [this.r, this.g, this.b]
		}
	}, toRgba: function () {
		return [this.r, this.g, this.b, this.a]
	}, toHex: function () {
		return dojo.gfx.color.rgb2hex(this.toRgb())
	}, toCss: function () {
		return 'rgb(' + this.toRgb().join() + ')'
	}, toString: function () {
		return this.toHex()
	}, blend: function (_3ac, _3ad) {
		var rgb = null;
		if (dojo.lang.isArray(_3ac)) {
			rgb = _3ac
		} else {
			if (_3ac instanceof dojo.gfx.color.Color) {
				rgb = _3ac.toRgb()
			} else {
				rgb = new dojo.gfx.color.Color(_3ac).toRgb()
			}
		}
		return dojo.gfx.color.blend(this.toRgb(), rgb, _3ad)
	}
});
dojo.gfx.color.named = {
	white: [255, 255, 255],
	black: [0, 0, 0],
	red: [255, 0, 0],
	green: [0, 255, 0],
	lime: [0, 255, 0],
	blue: [0, 0, 255],
	navy: [0, 0, 128],
	gray: [128, 128, 128],
	silver: [192, 192, 192]
};
dojo.gfx.color.blend = function (a, b, _3b1) {
	if (typeof a == 'string') {
		return dojo.gfx.color.blendHex(a, b, _3b1)
	}
	if (!_3b1) {
		_3b1 = 0
	}
	_3b1 = Math.min(Math.max(-1, _3b1), 1);
	_3b1 = ((_3b1 + 1) / 2);
	var c = [];
	for (var x = 0; x < 3; x++) {
		c[x] = parseInt(b[x] + ((a[x] - b[x]) * _3b1))
	}
	return c
};
dojo.gfx.color.blendHex = function (a, b, _3b6) {
	return dojo.gfx.color.rgb2hex(dojo.gfx.color.blend(dojo.gfx.color.hex2rgb(a), dojo.gfx.color.hex2rgb(b), _3b6))
};
dojo.gfx.color.extractRGB = function (_3b7) {
	var hex = '0123456789abcdef';
	_3b7 = _3b7.toLowerCase();
	if (_3b7.indexOf('rgb') == 0) {
		var _3b9 = _3b7.match(/rgba*\((\d+), *(\d+), *(\d+)/i);
		var ret = _3b9.splice(1, 3);
		return ret
	} else {
		var _3bb = dojo.gfx.color.hex2rgb(_3b7);
		if (_3bb) {
			return _3bb
		} else {
			return dojo.gfx.color.named[_3b7] || [255, 255, 255]
		}
	}
};
dojo.gfx.color.hex2rgb = function (hex) {
	var _3bd = '0123456789ABCDEF';
	var rgb = new Array(3);
	if (hex.indexOf('#') == 0) {
		hex = hex.substring(1)
	}
	hex = hex.toUpperCase();
	if (hex.replace(new RegExp('[' + _3bd + ']', 'g'), '') != '') {
		return null
	}
	if (hex.length == 3) {
		rgb[0] = hex.charAt(0) + hex.charAt(0);
		rgb[1] = hex.charAt(1) + hex.charAt(1);
		rgb[2] = hex.charAt(2) + hex.charAt(2)
	} else {
		rgb[0] = hex.substring(0, 2);
		rgb[1] = hex.substring(2, 4);
		rgb[2] = hex.substring(4)
	}
	for (var i = 0; i < rgb.length; i++) {
		rgb[i] = _3bd.indexOf(rgb[i].charAt(0)) * 16 + _3bd.indexOf(rgb[i].charAt(1))
	}
	return rgb
};
dojo.gfx.color.rgb2hex = function (r, g, b) {
	if (dojo.lang.isArray(r)) {
		g = r[1] || 0;
		b = r[2] || 0;
		r = r[0] || 0
	}
	var ret = dojo.lang.map([r, g, b], function (x) {
		x = Number(x);
		var s = x.toString(16);
		while (s.length < 2) {
			s = '0' + s
		}
		return s
	});
	ret.unshift('#');
	return ret.join('')
};
dojo.provide('dojo.lfx.Animation');
dojo.lfx.Line = function (_3c6, end) {
	this.start = _3c6;
	this.end = end;
	if (dojo.lang.isArray(_3c6)) {
		var diff = [];
		dojo.lang.forEach(this.start, function (s, i) {
			diff[i] = this.end[i] - s
		}, this);
		this.getValue = function (n) {
			var res = [];
			dojo.lang.forEach(this.start, function (s, i) {
				res[i] = (diff[i] * n) + s
			}, this);
			return res
		}
	} else {
		var diff = end - _3c6;
		this.getValue = function (n) {
			return (diff * n) + this.start
		}
	}
};
if ((dojo.render.html.khtml) && (!dojo.render.html.safari)) {
	dojo.lfx.easeDefault = function (n) {
		return (parseFloat('0.5') + ((Math.sin((n + parseFloat('1.5')) * Math.PI)) / 2))
	}
} else {
	dojo.lfx.easeDefault = function (n) {
		return (0.5 + ((Math.sin((n + 1.5) * Math.PI)) / 2))
	}
}
dojo.lfx.easeIn = function (n) {
	return Math.pow(n, 3)
};
dojo.lfx.easeOut = function (n) {
	return (1 - Math.pow(1 - n, 3))
};
dojo.lfx.easeInOut = function (n) {
	return ((3 * Math.pow(n, 2)) - (2 * Math.pow(n, 3)))
};
dojo.lfx.IAnimation = function () {
};
dojo.lang.extend(dojo.lfx.IAnimation, {
	curve: null,
	duration: 1000,
	easing: null,
	repeatCount: 0,
	rate: 10,
	handler: null,
	beforeBegin: null,
	onBegin: null,
	onAnimate: null,
	onEnd: null,
	onPlay: null,
	onPause: null,
	onStop: null,
	play: null,
	pause: null,
	stop: null,
	connect: function (evt, _3d6, _3d7) {
		if (!_3d7) {
			_3d7 = _3d6;
			_3d6 = this
		}
		_3d7 = dojo.lang.hitch(_3d6, _3d7);
		var _3d8 = this[evt] || function () {
		};
		this[evt] = function () {
			var ret = _3d8.apply(this, arguments);
			_3d7.apply(this, arguments);
			return ret
		};
		return this
	},
	fire: function (evt, args) {
		if (this[evt]) {
			this[evt].apply(this, (args || []))
		}
		return this
	},
	repeat: function (_3dc) {
		this.repeatCount = _3dc;
		return this
	},
	_active: false,
	_paused: false
});
dojo.lfx.Animation = function (_3dd, _3de, _3df, _3e0, _3e1, rate) {
	dojo.lfx.IAnimation.call(this);
	if (dojo.lang.isNumber(_3dd) || (!_3dd && _3de.getValue)) {
		rate = _3e1;
		_3e1 = _3e0;
		_3e0 = _3df;
		_3df = _3de;
		_3de = _3dd;
		_3dd = null
	} else {
		if (_3dd.getValue || dojo.lang.isArray(_3dd)) {
			rate = _3e0;
			_3e1 = _3df;
			_3e0 = _3de;
			_3df = _3dd;
			_3de = null;
			_3dd = null
		}
	}
	if (dojo.lang.isArray(_3df)) {
		this.curve = new dojo.lfx.Line(_3df[0], _3df[1])
	} else {
		this.curve = _3df
	}
	if (_3de != null && _3de > 0) {
		this.duration = _3de
	}
	if (_3e1) {
		this.repeatCount = _3e1
	}
	if (rate) {
		this.rate = rate
	}
	if (_3dd) {
		dojo.lang.forEach(['handler', 'beforeBegin', 'onBegin', 'onEnd', 'onPlay', 'onStop', 'onAnimate'], function (item) {
			if (_3dd[item]) {
				this.connect(item, _3dd[item])
			}
		}, this)
	}
	if (_3e0 && dojo.lang.isFunction(_3e0)) {
		this.easing = _3e0
	}
};
dojo.inherits(dojo.lfx.Animation, dojo.lfx.IAnimation);
dojo.lang.extend(dojo.lfx.Animation, {
	_startTime: null, _endTime: null, _timer: null, _percent: 0, _startRepeatCount: 0, play: function (_3e4, _3e5) {
		if (_3e5) {
			clearTimeout(this._timer);
			this._active = false;
			this._paused = false;
			this._percent = 0
		} else {
			if (this._active && !this._paused) {
				return this
			}
		}
		this.fire('handler', ['beforeBegin']);
		this.fire('beforeBegin');
		if (_3e4 > 0) {
			setTimeout(dojo.lang.hitch(this, function () {
				this.play(null, _3e5)
			}), _3e4);
			return this
		}
		this._startTime = new Date().valueOf();
		if (this._paused) {
			this._startTime -= (this.duration * this._percent / 100)
		}
		this._endTime = this._startTime + this.duration;
		this._active = true;
		this._paused = false;
		var step = this._percent / 100;
		var _3e7 = this.curve.getValue(step);
		if (this._percent == 0) {
			if (!this._startRepeatCount) {
				this._startRepeatCount = this.repeatCount
			}
			this.fire('handler', ['begin', _3e7]);
			this.fire('onBegin', [_3e7])
		}
		this.fire('handler', ['play', _3e7]);
		this.fire('onPlay', [_3e7]);
		this._cycle();
		return this
	}, pause: function () {
		clearTimeout(this._timer);
		if (!this._active) {
			return this
		}
		this._paused = true;
		var _3e8 = this.curve.getValue(this._percent / 100);
		this.fire('handler', ['pause', _3e8]);
		this.fire('onPause', [_3e8]);
		return this
	}, gotoPercent: function (pct, _3ea) {
		clearTimeout(this._timer);
		this._active = true;
		this._paused = true;
		this._percent = pct;
		if (_3ea) {
			this.play()
		}
		return this
	}, stop: function (_3eb) {
		clearTimeout(this._timer);
		var step = this._percent / 100;
		if (_3eb) {
			step = 1
		}
		var _3ed = this.curve.getValue(step);
		this.fire('handler', ['stop', _3ed]);
		this.fire('onStop', [_3ed]);
		this._active = false;
		this._paused = false;
		return this
	}, status: function () {
		if (this._active) {
			return this._paused ? 'paused' : 'playing'
		} else {
			return 'stopped'
		}
		return this
	}, _cycle: function () {
		clearTimeout(this._timer);
		if (this._active) {
			var curr = new Date().valueOf();
			var step = (curr - this._startTime) / (this._endTime - this._startTime);
			if (step >= 1) {
				step = 1;
				this._percent = 100
			} else {
				this._percent = step * 100
			}
			if ((this.easing) && (dojo.lang.isFunction(this.easing))) {
				step = this.easing(step)
			}
			var _3f0 = this.curve.getValue(step);
			this.fire('handler', ['animate', _3f0]);
			this.fire('onAnimate', [_3f0]);
			if (step < 1) {
				this._timer = setTimeout(dojo.lang.hitch(this, '_cycle'), this.rate)
			} else {
				this._active = false;
				this.fire('handler', ['end']);
				this.fire('onEnd');
				if (this.repeatCount > 0) {
					this.repeatCount--;
					this.play(null, true)
				} else {
					if (this.repeatCount == -1) {
						this.play(null, true)
					} else {
						if (this._startRepeatCount) {
							this.repeatCount = this._startRepeatCount;
							this._startRepeatCount = 0
						}
					}
				}
			}
		}
		return this
	}
});
dojo.lfx.Combine = function (_3f1) {
	dojo.lfx.IAnimation.call(this);
	this._anims = [];
	this._animsEnded = 0;
	var _3f2 = arguments;
	if (_3f2.length == 1 && (dojo.lang.isArray(_3f2[0]) || dojo.lang.isArrayLike(_3f2[0]))) {
		_3f2 = _3f2[0]
	}
	dojo.lang.forEach(_3f2, function (anim) {
		this._anims.push(anim);
		anim.connect('onEnd', dojo.lang.hitch(this, '_onAnimsEnded'))
	}, this)
};
dojo.inherits(dojo.lfx.Combine, dojo.lfx.IAnimation);
dojo.lang.extend(dojo.lfx.Combine, {
	_animsEnded: 0, play: function (_3f4, _3f5) {
		if (!this._anims.length) {
			return this
		}
		this.fire('beforeBegin');
		if (_3f4 > 0) {
			setTimeout(dojo.lang.hitch(this, function () {
				this.play(null, _3f5)
			}), _3f4);
			return this
		}
		if (_3f5 || this._anims[0].percent == 0) {
			this.fire('onBegin')
		}
		this.fire('onPlay');
		this._animsCall('play', null, _3f5);
		return this
	}, pause: function () {
		this.fire('onPause');
		this._animsCall('pause');
		return this
	}, stop: function (_3f6) {
		this.fire('onStop');
		this._animsCall('stop', _3f6);
		return this
	}, _onAnimsEnded: function () {
		this._animsEnded++;
		if (this._animsEnded >= this._anims.length) {
			this.fire('onEnd')
		}
		return this
	}, _animsCall: function (_3f7) {
		var args = [];
		if (arguments.length > 1) {
			for (var i = 1; i < arguments.length; i++) {
				args.push(arguments[i])
			}
		}
		var _3fa = this;
		dojo.lang.forEach(this._anims, function (anim) {
			anim[_3f7](args)
		}, _3fa);
		return this
	}
});
dojo.lfx.Chain = function (_3fc) {
	dojo.lfx.IAnimation.call(this);
	this._anims = [];
	this._currAnim = -1;
	var _3fd = arguments;
	if (_3fd.length == 1 && (dojo.lang.isArray(_3fd[0]) || dojo.lang.isArrayLike(_3fd[0]))) {
		_3fd = _3fd[0]
	}
	var _3fe = this;
	dojo.lang.forEach(_3fd, function (anim, i, _401) {
		this._anims.push(anim);
		if (i < _401.length - 1) {
			anim.connect('onEnd', dojo.lang.hitch(this, '_playNext'))
		} else {
			anim.connect('onEnd', dojo.lang.hitch(this, function () {
				this.fire('onEnd')
			}))
		}
	}, this)
};
dojo.inherits(dojo.lfx.Chain, dojo.lfx.IAnimation);
dojo.lang.extend(dojo.lfx.Chain, {
	_currAnim: -1, play: function (_402, _403) {
		if (!this._anims.length) {
			return this
		}
		if (_403 || !this._anims[this._currAnim]) {
			this._currAnim = 0
		}
		var _404 = this._anims[this._currAnim];
		this.fire('beforeBegin');
		if (_402 > 0) {
			setTimeout(dojo.lang.hitch(this, function () {
				this.play(null, _403)
			}), _402);
			return this
		}
		if (_404) {
			if (this._currAnim == 0) {
				this.fire('handler', ['begin', this._currAnim]);
				this.fire('onBegin', [this._currAnim])
			}
			this.fire('onPlay', [this._currAnim]);
			_404.play(null, _403)
		}
		return this
	}, pause: function () {
		if (this._anims[this._currAnim]) {
			this._anims[this._currAnim].pause();
			this.fire('onPause', [this._currAnim])
		}
		return this
	}, playPause: function () {
		if (this._anims.length == 0) {
			return this
		}
		if (this._currAnim == -1) {
			this._currAnim = 0
		}
		var _405 = this._anims[this._currAnim];
		if (_405) {
			if (!_405._active || _405._paused) {
				this.play()
			} else {
				this.pause()
			}
		}
		return this
	}, stop: function () {
		var _406 = this._anims[this._currAnim];
		if (_406) {
			_406.stop();
			this.fire('onStop', [this._currAnim])
		}
		return _406
	}, _playNext: function () {
		if (this._currAnim == -1 || this._anims.length == 0) {
			return this
		}
		this._currAnim++;
		if (this._anims[this._currAnim]) {
			this._anims[this._currAnim].play(null, true)
		}
		return this
	}
});
dojo.lfx.combine = function (_407) {
	var _408 = arguments;
	if (dojo.lang.isArray(arguments[0])) {
		_408 = arguments[0]
	}
	if (_408.length == 1) {
		return _408[0]
	}
	return new dojo.lfx.Combine(_408)
};
dojo.lfx.chain = function (_409) {
	var _40a = arguments;
	if (dojo.lang.isArray(arguments[0])) {
		_40a = arguments[0]
	}
	if (_40a.length == 1) {
		return _40a[0]
	}
	return new dojo.lfx.Chain(_40a)
};
dojo.provide('dojo.html.common');
dojo.lang.mixin(dojo.html, dojo.dom);
dojo.html.body = function () {
	dojo.deprecated('dojo.html.body() moved to dojo.body()', '0.5');
	return dojo.body()
};
dojo.html.getEventTarget = function (evt) {
	if (!evt) {
		evt = dojo.global().event || {}
	}
	var t = (evt.srcElement ? evt.srcElement : (evt.target ? evt.target : null));
	while ((t) && (t.nodeType != 1)) {
		t = t.parentNode
	}
	return t
};
dojo.html.getViewport = function () {
	var _40d = dojo.global();
	var _40e = dojo.doc();
	var w = 0;
	var h = 0;
	if (dojo.render.html.mozilla) {
		w = _40e.documentElement.clientWidth;
		h = _40d.innerHeight
	} else {
		if (!dojo.render.html.opera && _40d.innerWidth) {
			w = _40d.innerWidth;
			h = _40d.innerHeight
		} else {
			if (!dojo.render.html.opera && dojo.exists(_40e, 'documentElement.clientWidth')) {
				var w2 = _40e.documentElement.clientWidth;
				if (!w || w2 && w2 < w) {
					w = w2
				}
				h = _40e.documentElement.clientHeight
			} else {
				if (dojo.body().clientWidth) {
					w = dojo.body().clientWidth;
					h = dojo.body().clientHeight
				}
			}
		}
	}
	return {width: w, height: h}
};
dojo.html.getScroll = function () {
	var _412 = dojo.global();
	var _413 = dojo.doc();
	var top = _412.pageYOffset || _413.documentElement.scrollTop || dojo.body().scrollTop || 0;
	var left = _412.pageXOffset || _413.documentElement.scrollLeft || dojo.body().scrollLeft || 0;
	return {top: top, left: left, offset: {x: left, y: top}}
};
dojo.html.getParentByType = function (node, type) {
	var _418 = dojo.doc();
	var _419 = dojo.byId(node);
	type = type.toLowerCase();
	while ((_419) && (_419.nodeName.toLowerCase() != type)) {
		if (_419 == (_418['body'] || _418['documentElement'])) {
			return null
		}
		_419 = _419.parentNode
	}
	return _419
};
dojo.html.getAttribute = function (node, attr) {
	node = dojo.byId(node);
	if ((!node) || (!node.getAttribute)) {
		return null
	}
	var ta = typeof attr == 'string' ? attr : String(attr);
	var v = node.getAttribute(ta.toUpperCase());
	if ((v) && (typeof v == 'string') && (v != '')) {
		return v
	}
	if (v && v.value) {
		return v.value
	}
	if ((node.getAttributeNode) && (node.getAttributeNode(ta))) {
		return (node.getAttributeNode(ta)).value
	} else {
		if (node.getAttribute(ta)) {
			return node.getAttribute(ta)
		} else {
			if (node.getAttribute(ta.toLowerCase())) {
				return node.getAttribute(ta.toLowerCase())
			}
		}
	}
	return null
};
dojo.html.hasAttribute = function (node, attr) {
	return dojo.html.getAttribute(dojo.byId(node), attr) ? true : false
};
dojo.html.getCursorPosition = function (e) {
	e = e || dojo.global().event;
	var _421 = {x: 0, y: 0};
	if (e.pageX || e.pageY) {
		_421.x = e.pageX;
		_421.y = e.pageY
	} else {
		var de = dojo.doc().documentElement;
		var db = dojo.body();
		_421.x = e.clientX + ((de || db)['scrollLeft']) - ((de || db)['clientLeft']);
		_421.y = e.clientY + ((de || db)['scrollTop']) - ((de || db)['clientTop'])
	}
	return _421
};
dojo.html.isTag = function (node) {
	node = dojo.byId(node);
	if (node && node.tagName) {
		for (var i = 1; i < arguments.length; i++) {
			if (node.tagName.toLowerCase() == String(arguments[i]).toLowerCase()) {
				return String(arguments[i]).toLowerCase()
			}
		}
	}
	return ''
};
if (dojo.render.html.ie && !dojo.render.html.ie70) {
	if (window.location.href.substr(0, 6).toLowerCase() != 'https:') {
		(function () {
			var _426 = dojo.doc().createElement('script');
			_426.src = 'javascript:\'dojo.html.createExternalElement=function(doc, tag){ return doc.createElement(tag); }\'';
			dojo.doc().getElementsByTagName('head')[0].appendChild(_426)
		})()
	}
} else {
	dojo.html.createExternalElement = function (doc, tag) {
		return doc.createElement(tag)
	}
}
dojo.html._callDeprecated = function (_429, _42a, args, _42c, _42d) {
	dojo.deprecated('dojo.html.' + _429, 'replaced by dojo.html.' + _42a + '(' + (_42c ? 'node, {'
		+ _42c
		+ ': '
		+ _42c
		+ '}' : '') + ')' + (_42d ? '.' + _42d : ''), '0.5');
	var _42e = [];
	if (_42c) {
		var _42f = {};
		_42f[_42c] = args[1];
		_42e.push(args[0]);
		_42e.push(_42f)
	} else {
		_42e = args
	}
	var ret = dojo.html[_42a].apply(dojo.html, args);
	if (_42d) {
		return ret[_42d]
	} else {
		return ret
	}
};
dojo.html.getViewportWidth = function () {
	return dojo.html._callDeprecated('getViewportWidth', 'getViewport', arguments, null, 'width')
};
dojo.html.getViewportHeight = function () {
	return dojo.html._callDeprecated('getViewportHeight', 'getViewport', arguments, null, 'height')
};
dojo.html.getViewportSize = function () {
	return dojo.html._callDeprecated('getViewportSize', 'getViewport', arguments)
};
dojo.html.getScrollTop = function () {
	return dojo.html._callDeprecated('getScrollTop', 'getScroll', arguments, null, 'top')
};
dojo.html.getScrollLeft = function () {
	return dojo.html._callDeprecated('getScrollLeft', 'getScroll', arguments, null, 'left')
};
dojo.html.getScrollOffset = function () {
	return dojo.html._callDeprecated('getScrollOffset', 'getScroll', arguments, null, 'offset')
};
dojo.provide('dojo.uri.Uri');
dojo.uri = new function () {
	this.dojoUri = function (uri) {
		return new dojo.uri.Uri(dojo.hostenv.getBaseScriptUri(), uri)
	};
	this.moduleUri = function (_432, uri) {
		var loc = dojo.hostenv.getModuleSymbols(_432).join('/');
		if (!loc) {
			return null
		}
		if (loc.lastIndexOf('/') != loc.length - 1) {
			loc += '/'
		}
		var _435 = loc.indexOf(':');
		var _436 = loc.indexOf('/');
		if (loc.charAt(0) != '/' && (_435 == -1 || _435 > _436)) {
			loc = dojo.hostenv.getBaseScriptUri() + loc
		}
		return new dojo.uri.Uri(loc, uri)
	};
	this.Uri = function () {
		var uri = arguments[0];
		for (var i = 1; i < arguments.length; i++) {
			if (!arguments[i]) {
				continue
			}
			var _439 = new dojo.uri.Uri(arguments[i].toString());
			var _43a = new dojo.uri.Uri(uri.toString());
			if ((_439.path == '') && (_439.scheme == null) && (_439.authority == null) && (_439.query == null)) {
				if (_439.fragment != null) {
					_43a.fragment = _439.fragment
				}
				_439 = _43a
			} else {
				if (_439.scheme == null) {
					_439.scheme = _43a.scheme;
					if (_439.authority == null) {
						_439.authority = _43a.authority;
						if (_439.path.charAt(0) != '/') {
							var path = _43a.path.substring(0, _43a.path.lastIndexOf('/') + 1) + _439.path;
							var segs = path.split('/');
							for (var j = 0; j < segs.length; j++) {
								if (segs[j] == '.') {
									if (j == segs.length - 1) {
										segs[j] = ''
									} else {
										segs.splice(j, 1);
										j--
									}
								} else {
									if (j > 0 && !(j == 1 && segs[0] == '') && segs[j] == '..' && segs[j - 1] != '..') {
										if (j == segs.length - 1) {
											segs.splice(j, 1);
											segs[j - 1] = ''
										} else {
											segs.splice(j - 1, 2);
											j -= 2
										}
									}
								}
							}
							_439.path = segs.join('/')
						}
					}
				}
			}
			uri = '';
			if (_439.scheme != null) {
				uri += _439.scheme + ':'
			}
			if (_439.authority != null) {
				uri += '//' + _439.authority
			}
			uri += _439.path;
			if (_439.query != null) {
				uri += '?' + _439.query
			}
			if (_439.fragment != null) {
				uri += '#' + _439.fragment
			}
		}
		this.uri = uri.toString();
		var _43e = '^(([^:/?#]+):)?(//([^/?#]*))?([^?#]*)(\\?([^#]*))?(#(.*))?$';
		var r = this.uri.match(new RegExp(_43e));
		this.scheme = r[2] || (r[1] ? '' : null);
		this.authority = r[4] || (r[3] ? '' : null);
		this.path = r[5];
		this.query = r[7] || (r[6] ? '' : null);
		this.fragment = r[9] || (r[8] ? '' : null);
		if (this.authority != null) {
			_43e = '^((([^:]+:)?([^@]+))@)?([^:]*)(:([0-9]+))?$';
			r = this.authority.match(new RegExp(_43e));
			this.user = r[3] || null;
			this.password = r[4] || null;
			this.host = r[5];
			this.port = r[7] || null
		}
		this.toString = function () {
			return this.uri
		}
	}
};
;dojo.provide('dojo.html.style');
dojo.html.getClass = function (node) {
	node = dojo.byId(node);
	if (!node) {
		return ''
	}
	var cs = '';
	if (node.className) {
		cs = node.className
	} else {
		if (dojo.html.hasAttribute(node, 'class')) {
			cs = dojo.html.getAttribute(node, 'class')
		}
	}
	return cs.replace(/^\s+|\s+$/g, '')
};
dojo.html.getClasses = function (node) {
	var c = dojo.html.getClass(node);
	return (c == '') ? [] : c.split(/\s+/g)
};
dojo.html.hasClass = function (node, _445) {
	return (new RegExp('(^|\\s+)' + _445 + '(\\s+|$)')).test(dojo.html.getClass(node))
};
dojo.html.prependClass = function (node, _447) {
	_447 += ' ' + dojo.html.getClass(node);
	return dojo.html.setClass(node, _447)
};
dojo.html.addClass = function (node, _449) {
	if (dojo.html.hasClass(node, _449)) {
		return false
	}
	_449 = (dojo.html.getClass(node) + ' ' + _449).replace(/^\s+|\s+$/g, '');
	return dojo.html.setClass(node, _449)
};
dojo.html.setClass = function (node, _44b) {
	node = dojo.byId(node);
	var cs = String(_44b);
	try {
		if (typeof node.className == 'string') {
			node.className = cs
		} else {
			if (node.setAttribute) {
				node.setAttribute('class', _44b);
				node.className = cs
			} else {
				return false
			}
		}
	} catch (e) {
		dojo.debug('dojo.html.setClass() failed', e)
	}
	return true
};
dojo.html.removeClass = function (node, _44e, _44f) {
	try {
		if (!_44f) {
			var _450 = dojo.html.getClass(node).replace(new RegExp('(^|\\s+)' + _44e + '(\\s+|$)'), '$1$2')
		} else {
			var _450 = dojo.html.getClass(node).replace(_44e, '')
		}
		dojo.html.setClass(node, _450)
	} catch (e) {
		dojo.debug('dojo.html.removeClass() failed', e)
	}
	return true
};
dojo.html.replaceClass = function (node, _452, _453) {
	dojo.html.removeClass(node, _453);
	dojo.html.addClass(node, _452)
};
dojo.html.classMatchType = {ContainsAll: 0, ContainsAny: 1, IsOnly: 2};
dojo.html.getElementsByClass = function (_454, _455, _456, _457, _458) {
	_458 = false;
	var _459 = dojo.doc();
	_455 = dojo.byId(_455) || _459;
	var _45a = _454.split(/\s+/g);
	var _45b = [];
	if (_457 != 1 && _457 != 2) {
		_457 = 0
	}
	var _45c = new RegExp('(\\s|^)((' + _45a.join(')|(') + '))(\\s|$)');
	var _45d = _45a.join(' ').length;
	var _45e = [];
	if (!_458 && _459.evaluate) {
		var _45f = './/' + (_456 || '*') + '[contains(';
		if (_457 != dojo.html.classMatchType.ContainsAny) {
			_45f += 'concat(\' \',@class,\' \'), \' '
				+ _45a.join(' \') and contains(concat(\' \',@class,\' \'), \' ')
				+ ' \')';
			if (_457 == 2) {
				_45f += ' and string-length(@class)=' + _45d + ']'
			} else {
				_45f += ']'
			}
		} else {
			_45f += 'concat(\' \',@class,\' \'), \' '
				+ _45a.join(' \') or contains(concat(\' \',@class,\' \'), \' ')
				+ ' \')]'
		}
		var _460 = _459.evaluate(_45f, _455, null, XPathResult.ANY_TYPE, null);
		var _461 = _460.iterateNext();
		while (_461) {
			try {
				_45e.push(_461);
				_461 = _460.iterateNext()
			} catch (e) {
				break
			}
		}
		return _45e
	} else {
		if (!_456) {
			_456 = '*'
		}
		_45e = _455.getElementsByTagName(_456);
		var node, i = 0;
		outer:
			while (node = _45e[i++]) {
				var _464 = dojo.html.getClasses(node);
				if (_464.length == 0) {
					continue;
				}
				var _465 = 0;
				for (var j = 0; j < _464.length; j++) {
					if (_45c.test(_464[j])) {
						if (_457 == dojo.html.classMatchType.ContainsAny) {
							_45b.push(node);
							continue outer
						} else {
							_465++
						}
					} else {
						if (_457 == dojo.html.classMatchType.IsOnly) {
							continue outer
						}
					}
				}
				if (_465 == _45a.length) {
					if ((_457 == dojo.html.classMatchType.IsOnly) && (_465 == _464.length)) {
						_45b.push(node)
					} else {
						if (_457 == dojo.html.classMatchType.ContainsAll) {
							_45b.push(node)
						}
					}
				}
			}
		return _45b
	}
};
dojo.html.getElementsByClassName = dojo.html.getElementsByClass;
dojo.html.toCamelCase = function (_467) {
	var arr = _467.split('-'), cc = arr[0];
	for (var i = 1; i < arr.length; i++) {
		cc += arr[i].charAt(0).toUpperCase() + arr[i].substring(1)
	}
	return cc
};
dojo.html.toSelectorCase = function (_46b) {
	return _46b.replace(/([A-Z])/g, '-$1').toLowerCase()
};
if (dojo.render.html.ie) {
	dojo.html.getComputedStyle = function (node, _46d, _46e) {
		node = dojo.byId(node);
		if (!node || !node.currentStyle) {
			return _46e
		}
		return node.currentStyle[dojo.html.toCamelCase(_46d)]
	};
	dojo.html.getComputedStyles = function (node) {
		return node.currentStyle
	}
} else {
	dojo.html.getComputedStyle = function (node, _471, _472) {
		node = dojo.byId(node);
		if (!node || !node.style) {
			return _472
		}
		var s = document.defaultView.getComputedStyle(node, null);
		return (s && s[dojo.html.toCamelCase(_471)]) || ''
	};
	dojo.html.getComputedStyles = function (node) {
		return document.defaultView.getComputedStyle(node, null)
	}
}
dojo.html.getStyleProperty = function (node, _476) {
	node = dojo.byId(node);
	return (node && node.style ? node.style[dojo.html.toCamelCase(_476)] : undefined)
};
dojo.html.getStyle = function (node, _478) {
	var _479 = dojo.html.getStyleProperty(node, _478);
	return (_479 ? _479 : dojo.html.getComputedStyle(node, _478))
};
dojo.html.setStyle = function (node, _47b, _47c) {
	node = dojo.byId(node);
	if (node && node.style) {
		var _47d = dojo.html.toCamelCase(_47b);
		node.style[_47d] = _47c
	}
};
dojo.html.setStyleText = function (_47e, text) {
	try {
		_47e.style.cssText = text
	} catch (e) {
		_47e.setAttribute('style', text)
	}
};
dojo.html.copyStyle = function (_480, _481) {
	if (!_481.style.cssText) {
		_480.setAttribute('style', _481.getAttribute('style'))
	} else {
		_480.style.cssText = _481.style.cssText
	}
	dojo.html.addClass(_480, dojo.html.getClass(_481))
};
dojo.html.getUnitValue = function (node, _483, _484) {
	var s = dojo.html.getComputedStyle(node, _483);
	if ((!s) || ((s == 'auto') && (_484))) {
		return {value: 0, units: 'px'}
	}
	var _486 = s.match(/(\-?[\d.]+)([a-z%]*)/i);
	if (!_486) {
		return dojo.html.getUnitValue.bad
	}
	return {value: Number(_486[1]), units: _486[2].toLowerCase()}
};
dojo.html.getUnitValue.bad = {value: NaN, units: ''};
if (dojo.render.html.ie) {
	dojo.html.toPixelValue = function (_487, _488) {
		if (!_488) {
			return 0
		}
		if (_488.slice(-2) == 'px') {
			return parseFloat(_488)
		}
		var _489 = 0;
		with (_487) {
			var _48a = style.left;
			var _48b = runtimeStyle.left;
			runtimeStyle.left = currentStyle.left;
			try {
				style.left = _488 || 0;
				_489 = style.pixelLeft;
				style.left = _48a;
				runtimeStyle.left = _48b
			} catch (e) {
			}
		}
		return _489
	}
} else {
	dojo.html.toPixelValue = function (_48c, _48d) {
		return (_48d && (_48d.slice(-2) == 'px') ? parseFloat(_48d) : 0)
	}
}
dojo.html.getPixelValue = function (node, _48f, _490) {
	return dojo.html.toPixelValue(node, dojo.html.getComputedStyle(node, _48f))
};
dojo.html.setPositivePixelValue = function (node, _492, _493) {
	if (isNaN(_493)) {
		return false
	}
	node.style[_492] = Math.max(0, _493) + 'px';
	return true
};
dojo.html.styleSheet = null;
dojo.html.insertCssRule = function (_494, _495, _496) {
	if (!dojo.html.styleSheet) {
		if (document.createStyleSheet) {
			dojo.html.styleSheet = document.createStyleSheet()
		} else {
			if (document.styleSheets[0]) {
				dojo.html.styleSheet = document.styleSheets[0]
			} else {
				return null
			}
		}
	}
	if (arguments.length < 3) {
		if (dojo.html.styleSheet.cssRules) {
			_496 = dojo.html.styleSheet.cssRules.length
		} else {
			if (dojo.html.styleSheet.rules) {
				_496 = dojo.html.styleSheet.rules.length
			} else {
				return null
			}
		}
	}
	if (dojo.html.styleSheet.insertRule) {
		var rule = _494 + ' { ' + _495 + ' }';
		return dojo.html.styleSheet.insertRule(rule, _496)
	} else {
		if (dojo.html.styleSheet.addRule) {
			return dojo.html.styleSheet.addRule(_494, _495, _496)
		} else {
			return null
		}
	}
};
dojo.html.removeCssRule = function (_498) {
	if (!dojo.html.styleSheet) {
		dojo.debug('no stylesheet defined for removing rules');
		return false
	}
	if (dojo.render.html.ie) {
		if (!_498) {
			_498 = dojo.html.styleSheet.rules.length;
			dojo.html.styleSheet.removeRule(_498)
		}
	} else {
		if (document.styleSheets[0]) {
			if (!_498) {
				_498 = dojo.html.styleSheet.cssRules.length
			}
			dojo.html.styleSheet.deleteRule(_498)
		}
	}
	return true
};
dojo.html._insertedCssFiles = [];
dojo.html.insertCssFile = function (URI, doc, _49b, _49c) {
	if (!URI) {
		return
	}
	if (!doc) {
		doc = document
	}
	var _49d = dojo.hostenv.getText(URI, false, _49c);
	if (_49d === null) {
		return
	}
	_49d = dojo.html.fixPathsInCssText(_49d, URI);
	if (_49b) {
		var idx = -1, node, ent = dojo.html._insertedCssFiles;
		for (var i = 0; i < ent.length; i++) {
			if ((ent[i].doc == doc) && (ent[i].cssText == _49d)) {
				idx = i;
				node = ent[i].nodeRef;
				break
			}
		}
		if (node) {
			var _4a2 = doc.getElementsByTagName('style');
			for (var i = 0; i < _4a2.length; i++) {
				if (_4a2[i] == node) {
					return
				}
			}
			dojo.html._insertedCssFiles.shift(idx, 1)
		}
	}
	var _4a3 = dojo.html.insertCssText(_49d, doc);
	dojo.html._insertedCssFiles.push({'doc': doc, 'cssText': _49d, 'nodeRef': _4a3});
	if (_4a3 && djConfig.isDebug) {
		_4a3.setAttribute('dbgHref', URI)
	}
	return _4a3
};
dojo.html.insertCssText = function (_4a4, doc, URI) {
	if (!_4a4) {
		return
	}
	if (!doc) {
		doc = document
	}
	if (URI) {
		_4a4 = dojo.html.fixPathsInCssText(_4a4, URI)
	}
	var _4a7 = doc.createElement('style');
	_4a7.setAttribute('type', 'text/css');
	var head = doc.getElementsByTagName('head')[0];
	if (!head) {
		dojo.debug('No head tag in document, aborting styles');
		return
	} else {
		head.appendChild(_4a7)
	}
	if (_4a7.styleSheet) {
		var _4a9 = function () {
			try {
				_4a7.styleSheet.cssText = _4a4
			} catch (e) {
				dojo.debug(e)
			}
		};
		if (_4a7.styleSheet.disabled) {
			setTimeout(_4a9, 10)
		} else {
			_4a9()
		}
	} else {
		var _4aa = doc.createTextNode(_4a4);
		_4a7.appendChild(_4aa)
	}
	return _4a7
};
dojo.html.fixPathsInCssText = function (_4ab, URI) {
	if (!_4ab || !URI) {
		return
	}
	var _4ad, str = '', url = '', _4b0 = '[\\t\\s\\w\\(\\)\\/\\.\\\\\'"-:#=&?~]+';
	var _4b1 = new RegExp('url\\(\\s*(' + _4b0 + ')\\s*\\)');
	var _4b2 = /(file|https?|ftps?):\/\//;
	regexTrim = new RegExp('^[\\s]*([\'"]?)(' + _4b0 + ')\\1[\\s]*?$');
	if (dojo.render.html.ie55 || dojo.render.html.ie60) {
		var _4b3 = new RegExp('AlphaImageLoader\\((.*)src=[\'"](' + _4b0 + ')[\'"]');
		while (_4ad = _4b3.exec(_4ab)) {
			url = _4ad[2].replace(regexTrim, '$2');
			if (!_4b2.exec(url)) {
				url = (new dojo.uri.Uri(URI, url).toString())
			}
			str += _4ab.substring(0, _4ad.index) + 'AlphaImageLoader(' + _4ad[1] + 'src=\'' + url + '\'';
			_4ab = _4ab.substr(_4ad.index + _4ad[0].length)
		}
		_4ab = str + _4ab;
		str = ''
	}
	while (_4ad = _4b1.exec(_4ab)) {
		url = _4ad[1].replace(regexTrim, '$2');
		if (!_4b2.exec(url)) {
			url = (new dojo.uri.Uri(URI, url).toString())
		}
		str += _4ab.substring(0, _4ad.index) + 'url(' + url + ')';
		_4ab = _4ab.substr(_4ad.index + _4ad[0].length)
	}
	return str + _4ab
};
dojo.html.setActiveStyleSheet = function (_4b4) {
	var i = 0, a, els = dojo.doc().getElementsByTagName('link');
	while (a = els[i++]) {
		if (a.getAttribute('rel').indexOf('style') != -1 && a.getAttribute('title')) {
			a.disabled = true;
			if (a.getAttribute('title') == _4b4) {
				a.disabled = false
			}
		}
	}
};
dojo.html.getActiveStyleSheet = function () {
	var i = 0, a, els = dojo.doc().getElementsByTagName('link');
	while (a = els[i++]) {
		if (a.getAttribute('rel').indexOf('style') != -1 && a.getAttribute('title') && !a.disabled) {
			return a.getAttribute('title')
		}
	}
	return null
};
dojo.html.getPreferredStyleSheet = function () {
	var i = 0, a, els = dojo.doc().getElementsByTagName('link');
	while (a = els[i++]) {
		if (a.getAttribute('rel').indexOf('style')
			!= -1
			&& a.getAttribute('rel').indexOf('alt')
			== -1
			&& a.getAttribute('title')) {
			return a.getAttribute('title')
		}
	}
	return null
};
dojo.html.applyBrowserClass = function (node) {
	var drh = dojo.render.html;
	var _4c0 = {
		dj_ie: drh.ie,
		dj_ie55: drh.ie55,
		dj_ie6: drh.ie60,
		dj_ie7: drh.ie70,
		dj_iequirks: drh.ie && drh.quirks,
		dj_opera: drh.opera,
		dj_opera8: drh.opera && (Math.floor(dojo.render.version) == 8),
		dj_opera9: drh.opera && (Math.floor(dojo.render.version) == 9),
		dj_khtml: drh.khtml,
		dj_safari: drh.safari,
		dj_gecko: drh.mozilla
	};
	for (var p in _4c0) {
		if (_4c0[p]) {
			dojo.html.addClass(node, p)
		}
	}
};
dojo.provide('dojo.html.display');
dojo.html._toggle = function (node, _4c3, _4c4) {
	node = dojo.byId(node);
	_4c4(node, !_4c3(node));
	return _4c3(node)
};
dojo.html.show = function (node) {
	node = dojo.byId(node);
	if (dojo.html.getStyleProperty(node, 'display') == 'none') {
		dojo.html.setStyle(node, 'display', (node.dojoDisplayCache || ''));
		node.dojoDisplayCache = undefined
	}
};
dojo.html.hide = function (node) {
	node = dojo.byId(node);
	if (typeof node['dojoDisplayCache'] == 'undefined') {
		var d = dojo.html.getStyleProperty(node, 'display');
		if (d != 'none') {
			node.dojoDisplayCache = d
		}
	}
	dojo.html.setStyle(node, 'display', 'none')
};
dojo.html.setShowing = function (node, _4c9) {
	dojo.html[(_4c9 ? 'show' : 'hide')](node)
};
dojo.html.isShowing = function (node) {
	return (dojo.html.getStyleProperty(node, 'display') != 'none')
};
dojo.html.toggleShowing = function (node) {
	return dojo.html._toggle(node, dojo.html.isShowing, dojo.html.setShowing)
};
dojo.html.displayMap = {tr: '', td: '', th: '', img: 'inline', span: 'inline', input: 'inline', button: 'inline'};
dojo.html.suggestDisplayByTagName = function (node) {
	node = dojo.byId(node);
	if (node && node.tagName) {
		var tag = node.tagName.toLowerCase();
		return (tag in dojo.html.displayMap ? dojo.html.displayMap[tag] : 'block')
	}
};
dojo.html.setDisplay = function (node, _4cf) {
	dojo.html.setStyle(node, 'display', ((_4cf
		instanceof String
		|| typeof _4cf
		== 'string') ? _4cf : (_4cf ? dojo.html.suggestDisplayByTagName(node) : 'none')))
};
dojo.html.isDisplayed = function (node) {
	return (dojo.html.getComputedStyle(node, 'display') != 'none')
};
dojo.html.toggleDisplay = function (node) {
	return dojo.html._toggle(node, dojo.html.isDisplayed, dojo.html.setDisplay)
};
dojo.html.setVisibility = function (node, _4d3) {
	dojo.html.setStyle(node, 'visibility', ((_4d3
		instanceof String
		|| typeof _4d3
		== 'string') ? _4d3 : (_4d3 ? 'visible' : 'hidden')))
};
dojo.html.isVisible = function (node) {
	return (dojo.html.getComputedStyle(node, 'visibility') != 'hidden')
};
dojo.html.toggleVisibility = function (node) {
	return dojo.html._toggle(node, dojo.html.isVisible, dojo.html.setVisibility)
};
dojo.html.setOpacity = function (node, _4d7, _4d8) {
	node = dojo.byId(node);
	var h = dojo.render.html;
	if (!_4d8) {
		if (_4d7 >= 1) {
			if (h.ie) {
				dojo.html.clearOpacity(node);
				return
			} else {
				_4d7 = 0.999999
			}
		} else {
			if (_4d7 < 0) {
				_4d7 = 0
			}
		}
	}
	if (h.ie) {
		if (node.nodeName.toLowerCase() == 'tr') {
			var tds = node.getElementsByTagName('td');
			for (var x = 0; x < tds.length; x++) {
				tds[x].style.filter = 'Alpha(Opacity=' + _4d7 * 100 + ')'
			}
		}
		node.style.filter = 'Alpha(Opacity=' + _4d7 * 100 + ')'
	} else {
		if (h.moz) {
			node.style.opacity = _4d7;
			node.style.MozOpacity = _4d7
		} else {
			if (h.safari) {
				node.style.opacity = _4d7;
				node.style.KhtmlOpacity = _4d7
			} else {
				node.style.opacity = _4d7
			}
		}
	}
};
dojo.html.clearOpacity = function (node) {
	node = dojo.byId(node);
	var ns = node.style;
	var h = dojo.render.html;
	if (h.ie) {
		try {
			if (node.filters && node.filters.alpha) {
				ns.filter = ''
			}
		} catch (e) {
		}
	} else {
		if (h.moz) {
			ns.opacity = 1;
			ns.MozOpacity = 1
		} else {
			if (h.safari) {
				ns.opacity = 1;
				ns.KhtmlOpacity = 1
			} else {
				ns.opacity = 1
			}
		}
	}
};
dojo.html.getOpacity = function (node) {
	node = dojo.byId(node);
	var h = dojo.render.html;
	if (h.ie) {
		var opac = (node.filters
		&& node.filters.alpha
		&& typeof node.filters.alpha.opacity
		== 'number' ? node.filters.alpha.opacity : 100) / 100
	} else {
		var opac = node.style.opacity || node.style.MozOpacity || node.style.KhtmlOpacity || 1
	}
	return opac >= 0.999999 ? 1 : Number(opac)
};
dojo.provide('dojo.html.color');
dojo.html.getBackgroundColor = function (node) {
	node = dojo.byId(node);
	var _4e3;
	do {
		_4e3 = dojo.html.getStyle(node, 'background-color');
		if (_4e3.toLowerCase() == 'rgba(0, 0, 0, 0)') {
			_4e3 = 'transparent'
		}
		if (node == document.getElementsByTagName('body')[0]) {
			node = null;
			break
		}
		node = node.parentNode
	} while (node && dojo.lang.inArray(['transparent', ''], _4e3));
	if (_4e3 == 'transparent') {
		_4e3 = [255, 255, 255, 0]
	} else {
		_4e3 = dojo.gfx.color.extractRGB(_4e3)
	}
	return _4e3
};
dojo.provide('dojo.html.layout');
dojo.html.sumAncestorProperties = function (node, prop) {
	node = dojo.byId(node);
	if (!node) {
		return 0
	}
	var _4e6 = 0;
	while (node) {
		if (dojo.html.getComputedStyle(node, 'position') == 'fixed') {
			return 0
		}
		var val = node[prop];
		if (val) {
			_4e6 += val - 0;
			if (node == dojo.body()) {
				break
			}
		}
		node = node.parentNode
	}
	return _4e6
};
dojo.html.setStyleAttributes = function (node, _4e9) {
	node = dojo.byId(node);
	var _4ea = _4e9.replace(/(;)?\s*$/, '').split(';');
	for (var i = 0; i < _4ea.length; i++) {
		var _4ec = _4ea[i].split(':');
		var name = _4ec[0].replace(/\s*$/, '').replace(/^\s*/, '').toLowerCase();
		var _4ee = _4ec[1].replace(/\s*$/, '').replace(/^\s*/, '');
		switch (name) {
			case 'opacity':
				dojo.html.setOpacity(node, _4ee);
				break;
			case 'content-height':
				dojo.html.setContentBox(node, {height: _4ee});
				break;
			case 'content-width':
				dojo.html.setContentBox(node, {width: _4ee});
				break;
			case 'outer-height':
				dojo.html.setMarginBox(node, {height: _4ee});
				break;
			case 'outer-width':
				dojo.html.setMarginBox(node, {width: _4ee});
				break;
			default:
				node.style[dojo.html.toCamelCase(name)] = _4ee
		}
	}
};
dojo.html.boxSizing = {
	MARGIN_BOX: 'margin-box', BORDER_BOX: 'border-box', PADDING_BOX: 'padding-box', CONTENT_BOX: 'content-box'
};
dojo.html.getAbsolutePosition = dojo.html.abs = function (node, _4f0, _4f1) {
	node = dojo.byId(node, node.ownerDocument);
	var ret = {x: 0, y: 0};
	var bs = dojo.html.boxSizing;
	if (!_4f1) {
		_4f1 = bs.CONTENT_BOX
	}
	var _4f4 = 2;
	var _4f5;
	switch (_4f1) {
		case bs.MARGIN_BOX:
			_4f5 = 3;
			break;
		case bs.BORDER_BOX:
			_4f5 = 2;
			break;
		case bs.PADDING_BOX:
		default:
			_4f5 = 1;
			break;
		case bs.CONTENT_BOX:
			_4f5 = 0;
			break
	}
	var h = dojo.render.html;
	var db = document['body'] || document['documentElement'];
	if (h.ie) {
		with (node.getBoundingClientRect()) {
			ret.x = left - 2;
			ret.y = top - 2
		}
	} else {
		if (document.getBoxObjectFor) {
			_4f4 = 1;
			try {
				var bo = document.getBoxObjectFor(node);
				ret.x = bo.x - dojo.html.sumAncestorProperties(node, 'scrollLeft');
				ret.y = bo.y - dojo.html.sumAncestorProperties(node, 'scrollTop')
			} catch (e) {
			}
		} else {
			if (node['offsetParent']) {
				var _4f9;
				if ((h.safari) && (node.style.getPropertyValue('position') == 'absolute') && (node.parentNode == db)) {
					_4f9 = db
				} else {
					_4f9 = db.parentNode
				}
				if (node.parentNode != db) {
					var nd = node;
					if (dojo.render.html.opera) {
						nd = db
					}
					ret.x -= dojo.html.sumAncestorProperties(nd, 'scrollLeft');
					ret.y -= dojo.html.sumAncestorProperties(nd, 'scrollTop')
				}
				var _4fb = node;
				do {
					var n = _4fb['offsetLeft'];
					if (!h.opera || n > 0) {
						ret.x += isNaN(n) ? 0 : n
					}
					var m = _4fb['offsetTop'];
					ret.y += isNaN(m) ? 0 : m;
					_4fb = _4fb.offsetParent
				} while ((_4fb != _4f9) && (_4fb != null))
			} else {
				if (node['x'] && node['y']) {
					ret.x += isNaN(node.x) ? 0 : node.x;
					ret.y += isNaN(node.y) ? 0 : node.y
				}
			}
		}
	}
	if (_4f0) {
		var _4fe = dojo.html.getScroll();
		ret.y += _4fe.top;
		ret.x += _4fe.left
	}
	var _4ff = [dojo.html.getPaddingExtent, dojo.html.getBorderExtent, dojo.html.getMarginExtent];
	if (_4f4 > _4f5) {
		for (var i = _4f5; i < _4f4; ++i) {
			ret.y += _4ff[i](node, 'top');
			ret.x += _4ff[i](node, 'left')
		}
	} else {
		if (_4f4 < _4f5) {
			for (var i = _4f5; i > _4f4; --i) {
				ret.y -= _4ff[i - 1](node, 'top');
				ret.x -= _4ff[i - 1](node, 'left')
			}
		}
	}
	ret.top = ret.y;
	ret.left = ret.x;
	return ret
};
dojo.html.isPositionAbsolute = function (node) {
	return (dojo.html.getComputedStyle(node, 'position') == 'absolute')
};
dojo.html._sumPixelValues = function (node, _503, _504) {
	var _505 = 0;
	for (var x = 0; x < _503.length; x++) {
		_505 += dojo.html.getPixelValue(node, _503[x], _504)
	}
	return _505
};
dojo.html.getMargin = function (node) {
	return {
		width: dojo.html._sumPixelValues(node, ['margin-left', 'margin-right'], (dojo.html.getComputedStyle(node, 'position')
			== 'absolute')),
		height: dojo.html._sumPixelValues(node, ['margin-top', 'margin-bottom'], (dojo.html.getComputedStyle(node, 'position')
			== 'absolute'))
	}
};
dojo.html.getBorder = function (node) {
	return {
		width: dojo.html.getBorderExtent(node, 'left') + dojo.html.getBorderExtent(node, 'right'),
		height: dojo.html.getBorderExtent(node, 'top') + dojo.html.getBorderExtent(node, 'bottom')
	}
};
dojo.html.getBorderExtent = function (node, side) {
	return (dojo.html.getStyle(node, 'border-' + side + '-style') == 'none' ? 0 : dojo.html.getPixelValue(node, 'border-'
		+ side
		+ '-width'))
};
dojo.html.getMarginExtent = function (node, side) {
	return dojo.html._sumPixelValues(node, ['margin-' + side], dojo.html.isPositionAbsolute(node))
};
dojo.html.getPaddingExtent = function (node, side) {
	return dojo.html._sumPixelValues(node, ['padding-' + side], true)
};
dojo.html.getPadding = function (node) {
	return {
		width: dojo.html._sumPixelValues(node, ['padding-left', 'padding-right'], true),
		height: dojo.html._sumPixelValues(node, ['padding-top', 'padding-bottom'], true)
	}
};
dojo.html.getPadBorder = function (node) {
	var pad = dojo.html.getPadding(node);
	var _512 = dojo.html.getBorder(node);
	return {width: pad.width + _512.width, height: pad.height + _512.height}
};
dojo.html.getBoxSizing = function (node) {
	var h = dojo.render.html;
	var bs = dojo.html.boxSizing;
	if (((h.ie) || (h.opera)) && node.nodeName.toLowerCase() != 'img') {
		var cm = document['compatMode'];
		if ((cm == 'BackCompat') || (cm == 'QuirksMode')) {
			return bs.BORDER_BOX
		} else {
			return bs.CONTENT_BOX
		}
	} else {
		if (arguments.length == 0) {
			node = document.documentElement
		}
		var _517;
		if (!h.ie) {
			_517 = dojo.html.getStyle(node, '-moz-box-sizing');
			if (!_517) {
				_517 = dojo.html.getStyle(node, 'box-sizing')
			}
		}
		return (_517 ? _517 : bs.CONTENT_BOX)
	}
};
dojo.html.isBorderBox = function (node) {
	return (dojo.html.getBoxSizing(node) == dojo.html.boxSizing.BORDER_BOX)
};
dojo.html.getBorderBox = function (node) {
	node = dojo.byId(node);
	return {width: node.offsetWidth, height: node.offsetHeight}
};
dojo.html.getPaddingBox = function (node) {
	var box = dojo.html.getBorderBox(node);
	var _51c = dojo.html.getBorder(node);
	return {width: box.width - _51c.width, height: box.height - _51c.height}
};
dojo.html.getContentBox = function (node) {
	node = dojo.byId(node);
	var _51e = dojo.html.getPadBorder(node);
	return {width: node.offsetWidth - _51e.width, height: node.offsetHeight - _51e.height}
};
dojo.html.setContentBox = function (node, args) {
	node = dojo.byId(node);
	var _521 = 0;
	var _522 = 0;
	var isbb = dojo.html.isBorderBox(node);
	var _524 = (isbb ? dojo.html.getPadBorder(node) : {width: 0, height: 0});
	var ret = {};
	if (typeof args.width != 'undefined') {
		_521 = args.width + _524.width;
		ret.width = dojo.html.setPositivePixelValue(node, 'width', _521)
	}
	if (typeof args.height != 'undefined') {
		_522 = args.height + _524.height;
		ret.height = dojo.html.setPositivePixelValue(node, 'height', _522)
	}
	return ret
};
dojo.html.getMarginBox = function (node) {
	var _527 = dojo.html.getBorderBox(node);
	var _528 = dojo.html.getMargin(node);
	return {width: _527.width + _528.width, height: _527.height + _528.height}
};
dojo.html.setMarginBox = function (node, args) {
	node = dojo.byId(node);
	var _52b = 0;
	var _52c = 0;
	var isbb = dojo.html.isBorderBox(node);
	var _52e = (!isbb ? dojo.html.getPadBorder(node) : {width: 0, height: 0});
	var _52f = dojo.html.getMargin(node);
	var ret = {};
	if (typeof args.width != 'undefined') {
		_52b = args.width - _52e.width;
		_52b -= _52f.width;
		ret.width = dojo.html.setPositivePixelValue(node, 'width', _52b)
	}
	if (typeof args.height != 'undefined') {
		_52c = args.height - _52e.height;
		_52c -= _52f.height;
		ret.height = dojo.html.setPositivePixelValue(node, 'height', _52c)
	}
	return ret
};
dojo.html.getElementBox = function (node, type) {
	var bs = dojo.html.boxSizing;
	switch (type) {
		case bs.MARGIN_BOX:
			return dojo.html.getMarginBox(node);
		case bs.BORDER_BOX:
			return dojo.html.getBorderBox(node);
		case bs.PADDING_BOX:
			return dojo.html.getPaddingBox(node);
		case bs.CONTENT_BOX:
		default:
			return dojo.html.getContentBox(node)
	}
};
dojo.html.toCoordinateObject = dojo.html.toCoordinateArray = function (_534, _535, _536) {
	if (_534 instanceof Array || typeof _534 == 'array') {
		dojo.deprecated('dojo.html.toCoordinateArray', 'use dojo.html.toCoordinateObject({left: , top: , width: , height: }) instead', '0.5');
		while (_534.length < 4) {
			_534.push(0)
		}
		while (_534.length > 4) {
			_534.pop()
		}
		var ret = {left: _534[0], top: _534[1], width: _534[2], height: _534[3]}
	} else {
		if (!_534.nodeType && !(_534 instanceof String || typeof _534 == 'string') && ('width'
			in _534
			|| 'height'
			in _534
			|| 'left'
			in _534
			|| 'x'
			in _534
			|| 'top'
			in _534
			|| 'y'
			in _534)) {
			var ret = {
				left: _534.left || _534.x || 0, top: _534.top || _534.y || 0, width: _534.width || 0, height: _534.height || 0
			}
		} else {
			var node = dojo.byId(_534);
			var pos = dojo.html.abs(node, _535, _536);
			var _53a = dojo.html.getMarginBox(node);
			var ret = {left: pos.left, top: pos.top, width: _53a.width, height: _53a.height}
		}
	}
	ret.x = ret.left;
	ret.y = ret.top;
	return ret
};
dojo.html.setMarginBoxWidth = dojo.html.setOuterWidth = function (node, _53c) {
	return dojo.html._callDeprecated('setMarginBoxWidth', 'setMarginBox', arguments, 'width')
};
dojo.html.setMarginBoxHeight = dojo.html.setOuterHeight = function () {
	return dojo.html._callDeprecated('setMarginBoxHeight', 'setMarginBox', arguments, 'height')
};
dojo.html.getMarginBoxWidth = dojo.html.getOuterWidth = function () {
	return dojo.html._callDeprecated('getMarginBoxWidth', 'getMarginBox', arguments, null, 'width')
};
dojo.html.getMarginBoxHeight = dojo.html.getOuterHeight = function () {
	return dojo.html._callDeprecated('getMarginBoxHeight', 'getMarginBox', arguments, null, 'height')
};
dojo.html.getTotalOffset = function (node, type, _53f) {
	return dojo.html._callDeprecated('getTotalOffset', 'getAbsolutePosition', arguments, null, type)
};
dojo.html.getAbsoluteX = function (node, _541) {
	return dojo.html._callDeprecated('getAbsoluteX', 'getAbsolutePosition', arguments, null, 'x')
};
dojo.html.getAbsoluteY = function (node, _543) {
	return dojo.html._callDeprecated('getAbsoluteY', 'getAbsolutePosition', arguments, null, 'y')
};
dojo.html.totalOffsetLeft = function (node, _545) {
	return dojo.html._callDeprecated('totalOffsetLeft', 'getAbsolutePosition', arguments, null, 'left')
};
dojo.html.totalOffsetTop = function (node, _547) {
	return dojo.html._callDeprecated('totalOffsetTop', 'getAbsolutePosition', arguments, null, 'top')
};
dojo.html.getMarginWidth = function (node) {
	return dojo.html._callDeprecated('getMarginWidth', 'getMargin', arguments, null, 'width')
};
dojo.html.getMarginHeight = function (node) {
	return dojo.html._callDeprecated('getMarginHeight', 'getMargin', arguments, null, 'height')
};
dojo.html.getBorderWidth = function (node) {
	return dojo.html._callDeprecated('getBorderWidth', 'getBorder', arguments, null, 'width')
};
dojo.html.getBorderHeight = function (node) {
	return dojo.html._callDeprecated('getBorderHeight', 'getBorder', arguments, null, 'height')
};
dojo.html.getPaddingWidth = function (node) {
	return dojo.html._callDeprecated('getPaddingWidth', 'getPadding', arguments, null, 'width')
};
dojo.html.getPaddingHeight = function (node) {
	return dojo.html._callDeprecated('getPaddingHeight', 'getPadding', arguments, null, 'height')
};
dojo.html.getPadBorderWidth = function (node) {
	return dojo.html._callDeprecated('getPadBorderWidth', 'getPadBorder', arguments, null, 'width')
};
dojo.html.getPadBorderHeight = function (node) {
	return dojo.html._callDeprecated('getPadBorderHeight', 'getPadBorder', arguments, null, 'height')
};
dojo.html.getBorderBoxWidth = dojo.html.getInnerWidth = function () {
	return dojo.html._callDeprecated('getBorderBoxWidth', 'getBorderBox', arguments, null, 'width')
};
dojo.html.getBorderBoxHeight = dojo.html.getInnerHeight = function () {
	return dojo.html._callDeprecated('getBorderBoxHeight', 'getBorderBox', arguments, null, 'height')
};
dojo.html.getContentBoxWidth = dojo.html.getContentWidth = function () {
	return dojo.html._callDeprecated('getContentBoxWidth', 'getContentBox', arguments, null, 'width')
};
dojo.html.getContentBoxHeight = dojo.html.getContentHeight = function () {
	return dojo.html._callDeprecated('getContentBoxHeight', 'getContentBox', arguments, null, 'height')
};
dojo.html.setContentBoxWidth = dojo.html.setContentWidth = function (node, _551) {
	return dojo.html._callDeprecated('setContentBoxWidth', 'setContentBox', arguments, 'width')
};
dojo.html.setContentBoxHeight = dojo.html.setContentHeight = function (node, _553) {
	return dojo.html._callDeprecated('setContentBoxHeight', 'setContentBox', arguments, 'height')
};
dojo.provide('dojo.lfx.html');
dojo.lfx.html._byId = function (_554) {
	if (!_554) {
		return []
	}
	if (dojo.lang.isArrayLike(_554)) {
		if (!_554.alreadyChecked) {
			var n = [];
			dojo.lang.forEach(_554, function (node) {
				n.push(dojo.byId(node))
			});
			n.alreadyChecked = true;
			return n
		} else {
			return _554
		}
	} else {
		var n = [];
		n.push(dojo.byId(_554));
		n.alreadyChecked = true;
		return n
	}
};
dojo.lfx.html.propertyAnimation = function (_557, _558, _559, _55a, _55b) {
	_557 = dojo.lfx.html._byId(_557);
	var _55c = {'propertyMap': _558, 'nodes': _557, 'duration': _559, 'easing': _55a || dojo.lfx.easeDefault};
	var _55d = function (args) {
		if (args.nodes.length == 1) {
			var pm = args.propertyMap;
			if (!dojo.lang.isArray(args.propertyMap)) {
				var parr = [];
				for (var _561 in pm) {
					pm[_561].property = _561;
					parr.push(pm[_561])
				}
				pm = args.propertyMap = parr
			}
			dojo.lang.forEach(pm, function (prop) {
				if (dj_undef('start', prop)) {
					if (prop.property != 'opacity') {
						prop.start = parseInt(dojo.html.getComputedStyle(args.nodes[0], prop.property))
					} else {
						prop.start = dojo.html.getOpacity(args.nodes[0])
					}
				}
			})
		}
	};
	var _563 = function (_564) {
		var _565 = [];
		dojo.lang.forEach(_564, function (c) {
			_565.push(Math.round(c))
		});
		return _565
	};
	var _567 = function (n, _569) {
		n = dojo.byId(n);
		if (!n || !n.style) {
			return
		}
		for (var s in _569) {
			try {
				if (s == 'opacity') {
					dojo.html.setOpacity(n, _569[s])
				} else {
					n.style[s] = _569[s]
				}
			} catch (e) {
				dojo.debug(e)
			}
		}
	};
	var _56b = function (_56c) {
		this._properties = _56c;
		this.diffs = new Array(_56c.length);
		dojo.lang.forEach(_56c, function (prop, i) {
			if (dojo.lang.isFunction(prop.start)) {
				prop.start = prop.start(prop, i)
			}
			if (dojo.lang.isFunction(prop.end)) {
				prop.end = prop.end(prop, i)
			}
			if (dojo.lang.isArray(prop.start)) {
				this.diffs[i] = null
			} else {
				if (prop.start instanceof dojo.gfx.color.Color) {
					prop.startRgb = prop.start.toRgb();
					prop.endRgb = prop.end.toRgb()
				} else {
					this.diffs[i] = prop.end - prop.start
				}
			}
		}, this);
		this.getValue = function (n) {
			var ret = {};
			dojo.lang.forEach(this._properties, function (prop, i) {
				var _573 = null;
				if (dojo.lang.isArray(prop.start)) {
				} else {
					if (prop.start instanceof dojo.gfx.color.Color) {
						_573 = (prop.units || 'rgb') + '(';
						for (var j = 0; j < prop.startRgb.length; j++) {
							_573 += Math.round(((prop.endRgb[j] - prop.startRgb[j]) * n) + prop.startRgb[j]) + (j
							< prop.startRgb.length
							- 1 ? ',' : '')
						}
						_573 += ')'
					} else {
						_573 = ((this.diffs[i]) * n) + prop.start + (prop.property != 'opacity' ? prop.units || 'px' : '')
					}
				}
				ret[dojo.html.toCamelCase(prop.property)] = _573
			}, this);
			return ret
		}
	};
	var anim = new dojo.lfx.Animation({
		beforeBegin: function () {
			_55d(_55c);
			anim.curve = new _56b(_55c.propertyMap)
		}, onAnimate: function (_576) {
			dojo.lang.forEach(_55c.nodes, function (node) {
				_567(node, _576)
			})
		}
	}, _55c.duration, null, _55c.easing);
	if (_55b) {
		for (var x in _55b) {
			if (dojo.lang.isFunction(_55b[x])) {
				anim.connect(x, anim, _55b[x])
			}
		}
	}
	return anim
};
dojo.lfx.html._makeFadeable = function (_579) {
	var _57a = function (node) {
		if (dojo.render.html.ie) {
			if ((node.style.zoom.length == 0) && (dojo.html.getStyle(node, 'zoom') == 'normal')) {
				node.style.zoom = '1'
			}
			if ((node.style.width.length == 0) && (dojo.html.getStyle(node, 'width') == 'auto')) {
				node.style.width = 'auto'
			}
		}
	};
	if (dojo.lang.isArrayLike(_579)) {
		dojo.lang.forEach(_579, _57a)
	} else {
		_57a(_579)
	}
};
dojo.lfx.html.fade = function (_57c, _57d, _57e, _57f, _580) {
	_57c = dojo.lfx.html._byId(_57c);
	var _581 = {property: 'opacity'};
	if (!dj_undef('start', _57d)) {
		_581.start = _57d.start
	} else {
		_581.start = function () {
			return dojo.html.getOpacity(_57c[0])
		}
	}
	if (!dj_undef('end', _57d)) {
		_581.end = _57d.end
	} else {
		dojo.raise('dojo.lfx.html.fade needs an end value')
	}
	var anim = dojo.lfx.propertyAnimation(_57c, [_581], _57e, _57f);
	anim.connect('beforeBegin', function () {
		dojo.lfx.html._makeFadeable(_57c)
	});
	if (_580) {
		anim.connect('onEnd', function () {
			_580(_57c, anim)
		})
	}
	return anim
};
dojo.lfx.html.fadeIn = function (_583, _584, _585, _586) {
	return dojo.lfx.html.fade(_583, {end: 1}, _584, _585, _586)
};
dojo.lfx.html.fadeOut = function (_587, _588, _589, _58a) {
	return dojo.lfx.html.fade(_587, {end: 0}, _588, _589, _58a)
};
dojo.lfx.html.fadeShow = function (_58b, _58c, _58d, _58e) {
	_58b = dojo.lfx.html._byId(_58b);
	dojo.lang.forEach(_58b, function (node) {
		dojo.html.setOpacity(node, 0)
	});
	var anim = dojo.lfx.html.fadeIn(_58b, _58c, _58d, _58e);
	anim.connect('beforeBegin', function () {
		if (dojo.lang.isArrayLike(_58b)) {
			dojo.lang.forEach(_58b, dojo.html.show)
		} else {
			dojo.html.show(_58b)
		}
	});
	return anim
};
dojo.lfx.html.fadeHide = function (_591, _592, _593, _594) {
	var anim = dojo.lfx.html.fadeOut(_591, _592, _593, function () {
		if (dojo.lang.isArrayLike(_591)) {
			dojo.lang.forEach(_591, dojo.html.hide)
		} else {
			dojo.html.hide(_591)
		}
		if (_594) {
			_594(_591, anim)
		}
	});
	return anim
};
dojo.lfx.html.wipeIn = function (_596, _597, _598, _599) {
	_596 = dojo.lfx.html._byId(_596);
	var _59a = [];
	dojo.lang.forEach(_596, function (node) {
		var _59c = {};
		var _59d, _59e, _59f;
		with (node.style) {
			_59d = top;
			_59e = left;
			_59f = position;
			top = '-9999px';
			left = '-9999px';
			position = 'absolute';
			display = ''
		}
		var _5a0 = dojo.html.getBorderBox(node).height;
		with (node.style) {
			top = _59d;
			left = _59e;
			position = _59f;
			display = 'none'
		}
		var anim = dojo.lfx.propertyAnimation(node, {
			'height': {
				start: 1, end: function () {
					return _5a0
				}
			}
		}, _597, _598);
		anim.connect('beforeBegin', function () {
			_59c.overflow = node.style.overflow;
			_59c.height = node.style.height;
			with (node.style) {
				overflow = 'hidden';
				height = '1px'
			}
			dojo.html.show(node)
		});
		anim.connect('onEnd', function () {
			with (node.style) {
				overflow = _59c.overflow;
				height = _59c.height
			}
			if (_599) {
				_599(node, anim)
			}
		});
		_59a.push(anim)
	});
	return dojo.lfx.combine(_59a)
};
dojo.lfx.html.wipeOut = function (_5a2, _5a3, _5a4, _5a5) {
	_5a2 = dojo.lfx.html._byId(_5a2);
	var _5a6 = [];
	dojo.lang.forEach(_5a2, function (node) {
		var _5a8 = {};
		var anim = dojo.lfx.propertyAnimation(node, {
			'height': {
				start: function () {
					return dojo.html.getContentBox(node).height
				}, end: 1
			}
		}, _5a3, _5a4, {
			'beforeBegin': function () {
				_5a8.overflow = node.style.overflow;
				_5a8.height = node.style.height;
				with (node.style) {
					overflow = 'hidden'
				}
				dojo.html.show(node)
			}, 'onEnd': function () {
				dojo.html.hide(node);
				with (node.style) {
					overflow = _5a8.overflow;
					height = _5a8.height
				}
				if (_5a5) {
					_5a5(node, anim)
				}
			}
		});
		_5a6.push(anim)
	});
	return dojo.lfx.combine(_5a6)
};
dojo.lfx.html.slideTo = function (_5aa, _5ab, _5ac, _5ad, _5ae) {
	_5aa = dojo.lfx.html._byId(_5aa);
	var _5af = [];
	var _5b0 = dojo.html.getComputedStyle;
	if (dojo.lang.isArray(_5ab)) {
		dojo.deprecated('dojo.lfx.html.slideTo(node, array)', 'use dojo.lfx.html.slideTo(node, {top: value, left: value});', '0.5');
		_5ab = {top: _5ab[0], left: _5ab[1]}
	}
	dojo.lang.forEach(_5aa, function (node) {
		var top = null;
		var left = null;
		var init = (function () {
			var _5b5 = node;
			return function () {
				var pos = _5b0(_5b5, 'position');
				top = (pos == 'absolute' ? node.offsetTop : parseInt(_5b0(node, 'top')) || 0);
				left = (pos == 'absolute' ? node.offsetLeft : parseInt(_5b0(node, 'left')) || 0);
				if (!dojo.lang.inArray(['absolute', 'relative'], pos)) {
					var ret = dojo.html.abs(_5b5, true);
					dojo.html.setStyleAttributes(_5b5, 'position:absolute;top:' + ret.y + 'px;left:' + ret.x + 'px;');
					top = ret.y;
					left = ret.x
				}
			}
		})();
		init();
		var anim = dojo.lfx.propertyAnimation(node, {
			'top': {start: top, end: (_5ab.top || 0)}, 'left': {start: left, end: (_5ab.left || 0)}
		}, _5ac, _5ad, {'beforeBegin': init});
		if (_5ae) {
			anim.connect('onEnd', function () {
				_5ae(_5aa, anim)
			})
		}
		_5af.push(anim)
	});
	return dojo.lfx.combine(_5af)
};
dojo.lfx.html.slideBy = function (_5b9, _5ba, _5bb, _5bc, _5bd) {
	_5b9 = dojo.lfx.html._byId(_5b9);
	var _5be = [];
	var _5bf = dojo.html.getComputedStyle;
	if (dojo.lang.isArray(_5ba)) {
		dojo.deprecated('dojo.lfx.html.slideBy(node, array)', 'use dojo.lfx.html.slideBy(node, {top: value, left: value});', '0.5');
		_5ba = {top: _5ba[0], left: _5ba[1]}
	}
	dojo.lang.forEach(_5b9, function (node) {
		var top = null;
		var left = null;
		var init = (function () {
			var _5c4 = node;
			return function () {
				var pos = _5bf(_5c4, 'position');
				top = (pos == 'absolute' ? node.offsetTop : parseInt(_5bf(node, 'top')) || 0);
				left = (pos == 'absolute' ? node.offsetLeft : parseInt(_5bf(node, 'left')) || 0);
				if (!dojo.lang.inArray(['absolute', 'relative'], pos)) {
					var ret = dojo.html.abs(_5c4, true);
					dojo.html.setStyleAttributes(_5c4, 'position:absolute;top:' + ret.y + 'px;left:' + ret.x + 'px;');
					top = ret.y;
					left = ret.x
				}
			}
		})();
		init();
		var anim = dojo.lfx.propertyAnimation(node, {
			'top': {start: top, end: top + (_5ba.top || 0)}, 'left': {start: left, end: left + (_5ba.left || 0)}
		}, _5bb, _5bc).connect('beforeBegin', init);
		if (_5bd) {
			anim.connect('onEnd', function () {
				_5bd(_5b9, anim)
			})
		}
		_5be.push(anim)
	});
	return dojo.lfx.combine(_5be)
};
dojo.lfx.html.explode = function (_5c8, _5c9, _5ca, _5cb, _5cc) {
	var h = dojo.html;
	_5c8 = dojo.byId(_5c8);
	_5c9 = dojo.byId(_5c9);
	var _5ce = h.toCoordinateObject(_5c8, true);
	var _5cf = document.createElement('div');
	h.copyStyle(_5cf, _5c9);
	if (_5c9.explodeClassName) {
		_5cf.className = _5c9.explodeClassName
	}
	with (_5cf.style) {
		position = 'absolute';
		display = 'none';
		var _5d0 = h.getStyle(_5c8, 'background-color');
		backgroundColor = _5d0 ? _5d0.toLowerCase() : 'transparent';
		backgroundColor = (backgroundColor == 'transparent') ? 'rgb(221, 221, 221)' : backgroundColor
	}
	dojo.body().appendChild(_5cf);
	with (_5c9.style) {
		visibility = 'hidden';
		display = 'block'
	}
	var _5d1 = h.toCoordinateObject(_5c9, true);
	with (_5c9.style) {
		display = 'none';
		visibility = 'visible'
	}
	var _5d2 = {opacity: {start: 0.5, end: 1}};
	dojo.lang.forEach(['height', 'width', 'top', 'left'], function (type) {
		_5d2[type] = {start: _5ce[type], end: _5d1[type]}
	});
	var anim = new dojo.lfx.propertyAnimation(_5cf, _5d2, _5ca, _5cb, {
		'beforeBegin': function () {
			h.setDisplay(_5cf, 'block')
		}, 'onEnd': function () {
			h.setDisplay(_5c9, 'block');
			_5cf.parentNode.removeChild(_5cf)
		}
	});
	if (_5cc) {
		anim.connect('onEnd', function () {
			_5cc(_5c9, anim)
		})
	}
	return anim
};
dojo.lfx.html.implode = function (_5d5, end, _5d7, _5d8, _5d9) {
	var h = dojo.html;
	_5d5 = dojo.byId(_5d5);
	end = dojo.byId(end);
	var _5db = dojo.html.toCoordinateObject(_5d5, true);
	var _5dc = dojo.html.toCoordinateObject(end, true);
	var _5dd = document.createElement('div');
	dojo.html.copyStyle(_5dd, _5d5);
	if (_5d5.explodeClassName) {
		_5dd.className = _5d5.explodeClassName
	}
	dojo.html.setOpacity(_5dd, 0.3);
	with (_5dd.style) {
		position = 'absolute';
		display = 'none';
		backgroundColor = h.getStyle(_5d5, 'background-color').toLowerCase()
	}
	dojo.body().appendChild(_5dd);
	var _5de = {opacity: {start: 1, end: 0.5}};
	dojo.lang.forEach(['height', 'width', 'top', 'left'], function (type) {
		_5de[type] = {start: _5db[type], end: _5dc[type]}
	});
	var anim = new dojo.lfx.propertyAnimation(_5dd, _5de, _5d7, _5d8, {
		'beforeBegin': function () {
			dojo.html.hide(_5d5);
			dojo.html.show(_5dd)
		}, 'onEnd': function () {
			_5dd.parentNode.removeChild(_5dd)
		}
	});
	if (_5d9) {
		anim.connect('onEnd', function () {
			_5d9(_5d5, anim)
		})
	}
	return anim
};
dojo.lfx.html.highlight = function (_5e1, _5e2, _5e3, _5e4, _5e5) {
	_5e1 = dojo.lfx.html._byId(_5e1);
	var _5e6 = [];
	dojo.lang.forEach(_5e1, function (node) {
		var _5e8 = dojo.html.getBackgroundColor(node);
		var bg = dojo.html.getStyle(node, 'background-color').toLowerCase();
		var _5ea = dojo.html.getStyle(node, 'background-image');
		var _5eb = (bg == 'transparent' || bg == 'rgba(0, 0, 0, 0)');
		while (_5e8.length > 3) {
			_5e8.pop()
		}
		var rgb = new dojo.gfx.color.Color(_5e2);
		var _5ed = new dojo.gfx.color.Color(_5e8);
		var anim = dojo.lfx.propertyAnimation(node, {'background-color': {start: rgb, end: _5ed}}, _5e3, _5e4, {
			'beforeBegin': function () {
				if (_5ea) {
					node.style.backgroundImage = 'none'
				}
				node.style.backgroundColor = 'rgb(' + rgb.toRgb().join(',') + ')'
			}, 'onEnd': function () {
				if (_5ea) {
					node.style.backgroundImage = _5ea
				}
				if (_5eb) {
					node.style.backgroundColor = 'transparent'
				}
				if (_5e5) {
					_5e5(node, anim)
				}
			}
		});
		_5e6.push(anim)
	});
	return dojo.lfx.combine(_5e6)
};
dojo.lfx.html.unhighlight = function (_5ef, _5f0, _5f1, _5f2, _5f3) {
	_5ef = dojo.lfx.html._byId(_5ef);
	var _5f4 = [];
	dojo.lang.forEach(_5ef, function (node) {
		var _5f6 = new dojo.gfx.color.Color(dojo.html.getBackgroundColor(node));
		var rgb = new dojo.gfx.color.Color(_5f0);
		var _5f8 = dojo.html.getStyle(node, 'background-image');
		var anim = dojo.lfx.propertyAnimation(node, {'background-color': {start: _5f6, end: rgb}}, _5f1, _5f2, {
			'beforeBegin': function () {
				if (_5f8) {
					node.style.backgroundImage = 'none'
				}
				node.style.backgroundColor = 'rgb(' + _5f6.toRgb().join(',') + ')'
			}, 'onEnd': function () {
				if (_5f3) {
					_5f3(node, anim)
				}
			}
		});
		_5f4.push(anim)
	});
	return dojo.lfx.combine(_5f4)
};
dojo.lang.mixin(dojo.lfx, dojo.lfx.html);
dojo.kwCompoundRequire({browser: ['dojo.lfx.html'], dashboard: ['dojo.lfx.html']});
dojo.provide('dojo.lfx.*');

