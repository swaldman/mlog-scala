/*
 * Distributed as part of mlog-scala 0.3.7
 *
 * Copyright (C) 2015 Machinery For Change, Inc.
 *
 * Author: Steve Waldman <swaldman@mchange.com>
 *
 * This library is free software; you can redistribute it and/or modify
 * it under the terms of EITHER:
 *
 *     1) The GNU Lesser General Public License (LGPL), version 2.1, as 
 *        published by the Free Software Foundation
 *
 * OR
 *
 *     2) The Eclipse Public License (EPL), version 1.0
 *
 * You may choose which license to accept if you wish to redistribute
 * or modify this work. You may offer derivatives of this work
 * under the license you have chosen, or you may provide the same
 * choice of license which you have been offered here.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * You should have received copies of both LGPL v2.1 and EPL v1.0
 * along with this software; see the files LICENSE-EPL and LICENSE-LGPL.
 * If not, the text of these licenses are currently available at
 *
 * LGPL v2.1: http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 *  EPL v1.0: http://www.eclipse.org/org/documents/epl-v10.php 
 * 
 */

package com.mchange.sc.v1;

package object log {

  /**
   *  Conveniently log stuff when an Option turns up NONE
   */  
  implicit class LoggableOption[T]( val maybe : Option[T] ) extends AnyVal {
    import MLevel._

    def logFail( level : MLevel, message : =>String )( implicit logger : MLogger ) : Option[T] = {
      maybe orElse {
        logger.log( level, message )
        maybe
      }
    }
    def xall( message : =>String )( implicit logger : MLogger ) : Option[T] = logFail( ALL, message )( logger )
    def xconfig( message : =>String )( implicit logger : MLogger ) : Option[T] = logFail( CONFIG, message )( logger )
    def xfine( message : =>String )( implicit logger : MLogger ) : Option[T] = logFail( FINE, message )( logger )
    def xfiner( message : =>String )( implicit logger : MLogger ) : Option[T] = logFail( FINER, message )( logger )
    def xfinest( message : =>String )( implicit logger : MLogger ) : Option[T] = logFail( FINEST, message )( logger )
    def xinfo( message : =>String )( implicit logger : MLogger ) : Option[T] = logFail( INFO, message )( logger )
    def xsevere( message : =>String )( implicit logger : MLogger ) : Option[T] = logFail( SEVERE, message )( logger )
    def xwarning( message : =>String )( implicit logger : MLogger ) : Option[T] = logFail( WARNING, message )( logger )

    def xdebug( message : =>String )( implicit logger : MLogger ) : Option[T] = logFail( DEBUG, message )( logger )
    def xtrace( message : =>String )( implicit logger : MLogger ) : Option[T] = logFail( TRACE, message )( logger )

    def xwarn( message : =>String )( implicit logger : MLogger ) : Option[T] = xwarning( message )( logger )
  }
}
