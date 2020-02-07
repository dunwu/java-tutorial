<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="1.0">
	<xsl:template match="/">
		<xsl:apply-templates />
	</xsl:template>
	<xsl:template match="book">
		<html>
			<body>
				图书资料：
				<table>
					<tr>
						<td class='left'>作者：</td>
						<td class='right'>
							<xsl:value-of select="author" />
						</td>
					</tr>
					<tr>
						<td class='left'>书名：</td>
						<td class='right'>
							<xsl:value-of select="title" />
						</td>
					</tr>
					<tr>
						<td class='left'>类别：</td>
						<td class='right'>
							<xsl:value-of select="category" />
						</td>
					</tr>
					<tr>
						<td class='left'>定价：</td>
						<td class='right'>
							<xsl:value-of select="price" />
						</td>
					</tr>
				</table>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
