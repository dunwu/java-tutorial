<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">
	<xsl:output method="xml" omit-xml-declaration="no" />
	<xsl:template match="/">
		<xsl:apply-templates />
	</xsl:template>
	<xsl:template match="book">
		<xsl:element name="book">
			<xsl:attribute name="author">
				<xsl:value-of select="author" />
			</xsl:attribute>
			<xsl:element name="title">
				<xsl:value-of select="title" />
			</xsl:element>
		</xsl:element>
	</xsl:template>
</xsl:stylesheet>
