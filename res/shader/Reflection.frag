#version 120

#define REFLECTION_INTENSITY 0.6
#define REFLECTION_FALLOFF 0.2
#define REFLECTION_SPREAD 0.5
#define REFLECTION_BLUR_FACTOR 0.02

uniform sampler2D texture1;
uniform vec2 resolution;

uniform float locationMirror;

float map(float x, float inMin, float inMax, float outMin, float outMax){
	return (x - inMin) * (outMax - outMin) / (inMax - inMin) + outMin;
}

void main(){
	vec4 colorFinal = texture2D(texture1, gl_TexCoord[0].xy);
	
	if(gl_TexCoord[0].y < 1.0 - (locationMirror / resolution.y)){
		float dist = abs((1.0 - (locationMirror / resolution.y)) - gl_TexCoord[0].y);
		float distMult = max(map(dist, 0.0, REFLECTION_FALLOFF, 1.0, 0.0), 0.0);
		float xSample = gl_TexCoord[0].x + (0.5 - gl_TexCoord[0].x) * REFLECTION_SPREAD * dist;
		float ySample = 2.0 * (1.0 - (locationMirror / resolution.y)) - gl_TexCoord[0].y;
		
		vec4 colorSample = vec4(0.0);
		float samples = 0;
		for(float x = -dist * REFLECTION_BLUR_FACTOR; x <= dist * REFLECTION_BLUR_FACTOR; x += 1.0 / resolution.x){
			samples++;
			colorSample += texture2D(texture1, vec2(xSample + x, ySample));
		}
		
		colorFinal = (colorSample / samples) * distMult * REFLECTION_INTENSITY;
	}
	
	gl_FragColor = colorFinal;
}
