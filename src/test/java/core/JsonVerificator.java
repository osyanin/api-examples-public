package core;

import com.github.fge.jsonschema.SchemaVersion;
import com.github.fge.jsonschema.cfg.ValidationConfiguration;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

public class JsonVerificator {

	public JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory
			.newBuilder()
			.setValidationConfiguration(ValidationConfiguration
					.newBuilder()
					.setDefaultVersion(SchemaVersion.DRAFTV4)
					.freeze())
			.freeze();
}
