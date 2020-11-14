import com.soywiz.klock.hr.hrMilliseconds
import com.soywiz.klock.seconds
import com.soywiz.kmem.clamp
import com.soywiz.korev.Key
import com.soywiz.korge.*
import com.soywiz.korge.tiled.readTiledMap
import com.soywiz.korge.tiled.tiledMapView
import com.soywiz.korge.tween.*
import com.soywiz.korge.view.*
import com.soywiz.korim.color.Colors
import com.soywiz.korim.format.*
import com.soywiz.korio.file.std.*
import com.soywiz.korma.geom.degrees
import com.soywiz.korma.interpolation.Easing
import kotlin.math.pow

suspend fun main() = Korge() {
	val tiledMap = resourcesVfs["forGladFlowIsometric2.tmx"].readTiledMap()
    fixedSizeContainer(1280,720){
        position(0,0)
        val camera = camera{
            tiledMapView(tiledMap){
            }
        }
        var dx = 0.0
        var dy = 0.0
        addHrUpdater{
            val scale = if(it==0.hrMilliseconds) 0.0 else (it/16.666666.hrMilliseconds)
            if(views.input.keys[Key.RIGHT])dx-=1.0
            if(views.input.keys[Key.LEFT])dx+=1.0
            if(views.input.keys[Key.UP])dy+=1.0
            if(views.input.keys[Key.DOWN])dy-=1.0
            dx=dx.clamp(-10.0, +10.0)
            dy=dy.clamp(-10.0, +10.0)
            camera.x+=dx*scale
            camera.y+=dy*scale
            dx*=0.9.pow(scale)
            dy*=0.9.pow(scale)
        }
    }
}