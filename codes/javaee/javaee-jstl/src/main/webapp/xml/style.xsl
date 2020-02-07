<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

	<xsl:output method="html" indent="yes" />

	<xsl:template match="/">
		<html>
			<body>
				<xsl:apply-templates />
			</body>
		</html>
	</xsl:template>

	<xsl:template match="books">
		<table border="1" width="100%">
			<xsl:for-each select="book">
				<tr>
					<td>
						<i>
							<xsl:value-of select="name" />
						</i>
					</td>
					<td>
						<xsl:value-of select="author" />
					</td>
					<td>
						<xsl:value-of select="price" />
					</td>
				</tr>
			</xsl:for-each>
		</table>
	</xsl:template>
