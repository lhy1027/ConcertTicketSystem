package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.awt.*;
import java.net.URI;

@SpringBootApplication
@MapperScan(basePackages = {"com.dao"})
public class SpringbootSchemaApplication extends SpringBootServletInitializer implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootSchemaApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder applicationBuilder) {
		return applicationBuilder.sources(SpringbootSchemaApplication.class);
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		new Thread(() -> {
			try {
				Thread.sleep(3000); // 等待服务器完全启动
				String backend = "http://localhost:8080/springbooth56pqtxo/admin/dist/index.html#/login";
				String frontend = "http://localhost:8080/springbooth56pqtxo/front/dist/index.html#/login";
				openBrowser(backend);
				Thread.sleep(500); // 避免浏览器覆盖第一个标签页
				openBrowser(frontend);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}).start();
	}

	private void openBrowser(String url) {
		if (Desktop.isDesktopSupported()) {
			Desktop desktop = Desktop.getDesktop();
			if (desktop.isSupported(Desktop.Action.BROWSE)) {
				try {
					desktop.browse(new URI(url));
					System.out.println("已打开: " + url);
					return;
				} catch (Exception e) {
					System.err.println("Desktop.browse 失败: " + e.getMessage());
				}
			}
		}
		String os = System.getProperty("os.name").toLowerCase();
		Runtime runtime = Runtime.getRuntime();
		try {
			if (os.contains("win"))
				runtime.exec("rundll32 url.dll,FileProtocolHandler " + url);
			else if (os.contains("mac"))
				runtime.exec("open " + url);
			else if (os.contains("nix") || os.contains("nux"))
				runtime.exec("xdg-open " + url);
			else
				System.err.println("未知操作系统，无法自动打开浏览器");
			System.out.println("已通过命令打开: " + url);
		} catch (Exception e) {
			System.err.println("命令打开失败: " + e.getMessage());
		}
	}
}